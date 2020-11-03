package com.github.thestyleofme.dubbo.threadpool;

import java.util.Map;
import java.util.concurrent.*;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/04 2:51
 * @since 1.0.0
 */
public class WatchingThreadPool extends FixedThreadPool implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WatchingThreadPool.class);
    /**
     * 超过90%警告
     */
    private static final double ALARM_PERCENT = 0.90D;
    private static final Map<URL, ThreadPoolExecutor> THREAD_POOLS = new ConcurrentHashMap<>();

    public WatchingThreadPool() {
        // 每隔三秒报告线程使用情况
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(this, 1L, 3L, TimeUnit.SECONDS);
    }

    @Override
    public Executor getExecutor(URL url) {
        Executor executor = super.getExecutor(url);
        if (executor instanceof ThreadPoolExecutor) {
            THREAD_POOLS.put(url, (ThreadPoolExecutor) executor);
        }
        return executor;
    }

    @Override
    public void run() {
        THREAD_POOLS.forEach((url, threadPoolExecutor) -> {
            int activeCount = threadPoolExecutor.getActiveCount();
            int corePoolSize = threadPoolExecutor.getCorePoolSize();
            double usedPercent = activeCount / (corePoolSize * 1.0);
            LOGGER.info("threadPool status: [{}/{}: {}%]", activeCount, corePoolSize, usedPercent * 100);
            if (usedPercent > ALARM_PERCENT) {
                LOGGER.warn("threadPool alarm, host: {}, current used: {}%, URL: {}",
                        url.getIp(), usedPercent * 100, url);
            }
        });
    }
}
