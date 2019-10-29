package top.idwangmo.whitebird.commoncore.util;

import cn.hutool.core.date.SystemClock;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 发号器.
 * <p>
 * 参考实现<a>https://gitee.com/yu120/sequence.git</a>
 *
 * @author idwangmo
 */
public class Sequence {

    /**
     * 起始时间戳
     */
    private final static long START_TIMESTAMP = 1572332572L;

    /**
     * dataCenterId 占用位数。
     * <p>
     * twitter 的 snowflake 算法是5位，但是一般小公司是不会有那么多的数据中心的，所以就配置成2位.
     */
    private final static long DATA_CENTER_ID_BITS = 2L;

    /**
     * workId 的占用位数
     */
    private final static long WORKER_ID_BITS = 8L;

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
    private static String instanceIdStc;
    private final long workerId;
    private final long dataCenterId;
    private final boolean clock;
    private final long timeOffset;
    private final boolean randomSequence;
    private final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    @Value("${spring.cloud.consul.discovery.instance-id}")
    private String instanceId;

    public Sequence(long dataCenterId) {
        this(getWorkerId(), dataCenterId, false, 5L, false);
    }

    public Sequence(long dataCenterId, boolean clock, boolean randomSequence) {
        this(getWorkerId(), dataCenterId, clock, 5L, randomSequence);
    }

    public Sequence(long workerId, long dataCenterId, boolean clock, long timeOffset, boolean randomSequence) {

        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException("Data Center ID can't be greater than " + MAX_DATA_CENTER_ID);
        }

        if (workerId > MAX_WORK_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID can't be greater than " + MAX_WORK_ID);
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.clock = clock;
        this.timeOffset = timeOffset;
        this.randomSequence = randomSequence;
    }

    /**
     * 用 Spring Cloud 实例的 ID 加上直接 hash 取最低的几位.
     */
    public static long getWorkerId() {
        return (instanceIdStc.hashCode() & 0XFFFF) % (MAX_WORK_ID + 1);
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
            tempSequence = tempSequence & SEQUENCE_MASK;
            if (sequence == 0) {
                currentTimestamp = this.tilNextMillis(lastTimestamp);
            }
        } else {
            // randomSequence为true表示随机生成允许范围内的序列起始值,否则毫秒内起始值为0L开始自增
            sequence = randomSequence ? threadLocalRandom.nextLong(SEQUENCE_MASK + 1) : 0L;
        }

        lastTimestamp = currentTimestamp;
        long currentOffset = currentTimestamp - START_TIMESTAMP;

        // 通过移位将数据移动到合适的位置上
        return (currentOffset << TIMESTAMP_LIFT_SHIFT) |
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
        return clock ? SystemClock.now() : System.currentTimeMillis();
    }
}
