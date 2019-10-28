package top.idwangmo.whitebird.commoncore.lock;

/**
 * 分布式锁接口.
 *
 * @author idwangmo
 */
public interface DistributedLock {

    /**
     * 默认的超市时间
     */
    long TIMEOUT_MILLIS = 5000L;

    /**
     * 默认的重试次数.
     */
    long RETRY_TIMES = 16L;

    /**
     * 每次重试后等等的时间
     */
    long WAIT_MILLIS = 100L;

    /**
     * 获取锁.
     *
     * @param key 唯一key
     * @return 状态
     */
    boolean lock(String key);

    /**
     * 获取锁.
     *
     * @param key        唯一key
     * @param retryTimes 重试次数
     * @return 状态
     */
    boolean lock(String key, long retryTimes);

    /**
     * 获取锁.
     *
     * @param key        唯一key
     * @param retryTimes 重试次数
     * @param waitMillis 重试等待时间
     * @return 状态
     */
    boolean lock(String key, long retryTimes, long waitMillis);

    /**
     * 释放锁.
     *
     * @param key 唯一值
     * @return 状态
     */
    boolean release(String key);

}
