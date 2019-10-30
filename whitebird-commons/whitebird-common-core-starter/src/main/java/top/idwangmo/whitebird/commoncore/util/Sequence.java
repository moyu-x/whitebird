package top.idwangmo.whitebird.commoncore.util;

import cn.hutool.core.date.SystemClock;
import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 发号器.
 * <p>
 * 参考实现<a>https://gitee.com/yu120/sequence.git</a>
 *
 * 如果业务量较小，并且只会在单数据中心部署，为了前端处理上的方便，可以修改为最长53位的方式，具体为删除数据中心的标识位，改用32
 * 位的时间戳，提高自增序列的长度为16就行。移位运算如下
 *
 * ( 时间戳 << 21 ) | ( 机器标识 << 16 ) | 数据自增
 *
 * @author idwangmo
 */
public class Sequence {

    /**
     * 起始时间戳，是毫秒时间戳，在新系统中使用的时候可以修改这个数为最近的时间
     */
    private final static long START_TIMESTAMP = 1572400915195L;

    /**
     * dataCenterId 占用位数。
     * <p>
     * twitter 的 snowflake 算法是5位，但是一般小公司是不会有那么多的数据中心的，所以就配置成2位.
     * 并且如果确定是只会在一个数据中心中进行部署，可以删除数据中心的标识位
     */
    private final static long DATA_CENTER_ID_BITS = 2L;

    /**
     * workId 的占用位数，最大值用2为底数的幂运算可以得到
     */
    private final static long WORKER_ID_BITS = 5L;

    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BITS = 12L;

    /**
     * workId 可以使用的范围
     */
    private final static long MAX_WORK_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * dataCenterId 可以使用的范围
     */
    private final static long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;

    private final static long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    private final static long TIMESTAMP_LIFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 防止递增后溢出.
     */
    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private final long workerId;
    private final long dataCenterId;
    private final long timeOffset;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private static String instanceIdStc;
    private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
    /**
     * 随机递增使用
     */
    private boolean randomSequence;
    /**
     * 利用 Spring Cloud discovery instance id 获取 worker id
     */
    @Value("${spring.cloud.consul.discovery.instance-id}")
    private String instanceId;

    public Sequence() {
        this(getDataCenterId(), getWorkerId(), 5L, false);
    }

    /**
     * Sequence 构造器.
     *
     * @param dataCenterId 数据中心ID
     */
    public Sequence(long dataCenterId) {
        this(dataCenterId, getWorkerId(), 5L, false);
    }

    /**
     * Sequence 构造器.
     *
     * @param dataCenterId 数据中心ID
     * @param workerId     工作机器标识
     */
    public Sequence(long dataCenterId, long workerId) {
        this(dataCenterId, workerId, 5L, false);
    }

    /**
     * Sequence 构造器.
     *
     * @param dataCenterId   数据中心标识
     * @param workerId       工作机器标识
     * @param timeOffset     允许偏移的时间
     * @param randomSequence 是否从随机位置开始自增
     */
    public Sequence(long dataCenterId, long workerId, long timeOffset, boolean randomSequence) {

        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("Data Center ID can't be greater than " + MAX_DATA_CENTER_ID);
        }

        if (workerId > MAX_WORK_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID can't be greater than " + MAX_WORK_ID);
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.timeOffset = timeOffset;
        this.randomSequence = randomSequence;
    }

    /**
     * 用 Spring Cloud 实例的 ID 加上直接 hash 取最低的几位.
     *
     * 如果 instanceId 为空，则使用 ip 获取最后一部分数据
     */
    public static long getWorkerId() {
        if (Objects.nonNull(instanceIdStc)) {
            return (instanceIdStc.hashCode() & 0XFFFF) % (MAX_WORK_ID + 1);
        }

        long ip = 0;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            byte[] addressByte = inetAddress.getAddress();
            ip = addressByte[addressByte.length - 1];
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown Host Exception", e);
        }
        return ip % (MAX_WORK_ID + 1);
    }

    /**
     * 当为空构造器的时候获取数据中心id，用 mac + ip 的 hashcode 取最低几位
     */
    public static long getDataCenterId() {
        long id = 0L;

        try {
            String mac;
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            if (Objects.isNull(network)) {
                mac = UUID.randomUUID().toString();
            } else {
                byte[] hardwareAddress = network.getHardwareAddress();
                mac = new String(hardwareAddress);
            }

            id = ((new String(ip.getAddress()) + mac).hashCode() & 0x000000FF) % (MAX_DATA_CENTER_ID + 1);

        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }

        return id;
    }

    @PostConstruct
    public void init() {
        instanceIdStc = instanceId;
    }

    /**
     * 获取 ID
     */
    public synchronized Long nextId() {

        long currentTimestamp = this.timeGen();

        // 闰秒： 如果当前时间小于上一次生成ID的时间并且小于偏移量，就抛出异常
        if (currentTimestamp < lastTimestamp) {
            long currentOffset = lastTimestamp - currentTimestamp;

            if (currentOffset > timeOffset) {
                throw new RuntimeException("Clock moved backwards, refusing to generate id for [" + currentOffset +
                        "ms]");
            }

            try {
                // 在允许的回退时间内允许2倍时间偏移后获取
                this.wait(currentOffset << 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            currentTimestamp = this.timeGen();

            if (currentTimestamp < lastTimestamp) {
                throw new RuntimeException(
                        "Clock moved backwards, refusing to generate id for [" + currentOffset + "ms]");
            }

        } else if (currentTimestamp == lastTimestamp) {
            long tempSequence = sequence + 1;
            if (randomSequence && tempSequence > SEQUENCE_MASK) {
                tempSequence = tempSequence % SEQUENCE_MASK;
            }

            // 通过运算保证计算结果在给定的范围内
            sequence = tempSequence & SEQUENCE_MASK;
            if (sequence == 0) {
                currentTimestamp = this.tilNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒内，序列号更具要求自增
            sequence = randomSequence ? threadLocalRandom.nextLong(SEQUENCE_MASK + 1) : 0L;
        }

        lastTimestamp = currentTimestamp;
        long currentTimestampOffset = currentTimestamp - START_TIMESTAMP;

        // 通过移位将数据移动到合适的位置上
        return (currentTimestampOffset << TIMESTAMP_LIFT_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;

    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();

        // 出现时间回拨，有可能是闰秒，则重新自动获取
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }

        return timestamp;
    }

    private long timeGen() {
        return SystemClock.now();
    }

}
