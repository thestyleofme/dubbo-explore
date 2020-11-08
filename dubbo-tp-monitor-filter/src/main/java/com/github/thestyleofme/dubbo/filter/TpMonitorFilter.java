package com.github.thestyleofme.dubbo.filter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/09 0:39
 * @since 1.0.0
 */
@Activate(group = {CommonConstants.CONSUMER})
public class TpMonitorFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Map<String, List<MethodMonitor>> methodMonitorMap = new ConcurrentHashMap<>(8);
    private final Object lock = new Object();
    private static final int LOG_NUMBER = 500;
    private final AtomicBoolean started = new AtomicBoolean();

    public TpMonitorFilter() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("tp-monitor-%d").build();
        new ScheduledThreadPoolExecutor(1, namedThreadFactory)
                .scheduleWithFixedDelay(this::testTp90Tp99, 2, 5, TimeUnit.SECONDS);
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        started.set(true);
        long begin = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        long cost = System.currentTimeMillis() - begin;
        synchronized (lock) {
            String methodName = invocation.getMethodName();
            List<MethodMonitor> methodMonitors = Optional.ofNullable(methodMonitorMap.get(methodName))
                    .orElse(new ArrayList<>());
            if (methodMonitors.size() % LOG_NUMBER == 0) {
                logger.info("now: {}, method [{}], invoke number: {}", LocalDateTime.now(), methodName, methodMonitors.size());
            }
            methodMonitors.add(new MethodMonitor(cost, begin));
            methodMonitorMap.put(methodName, methodMonitors);
        }
        return result;
    }

    /**
     * tp90 tp99是指的一个时间概念
     * tp90就是得出来一个时间  保证有90%的方法可以在这个时间内完成
     * tp99就是得出来一个时间  保证有99%的方法可以在这个时间内完成
     */
    private void testTp90Tp99() {
        if (!started.get()) {
            return;
        }
        // 每隔5s打印一次最近1分钟内每个方法的TP90、TP99的耗时情况
        methodMonitorMap.forEach((method, methodMonitors) -> {
            // 找到最近1分钟的监控数据并排序
            long lastOneMinute = System.currentTimeMillis() - 60000;
            List<MethodMonitor> lastOneMinuteList = methodMonitors.stream()
                    .filter(methodMonitor -> methodMonitor.begin > lastOneMinute)
                    .sorted(Comparator.comparingInt(o -> (int) o.cost))
                    .collect(Collectors.toList());
            int tp90Index = lastOneMinuteList.size() * 9 / 10;
            logger.info("now: {}, method: {}, TP90: {}ms", LocalDateTime.now(), method, lastOneMinuteList.get(tp90Index).getCost());
            int tp99Index = lastOneMinuteList.size() * 99 / 100;
            logger.info("now: {}, method: {}, TP99: {}ms", LocalDateTime.now(), method, lastOneMinuteList.get(tp99Index).getCost());
        });
    }

    public static class MethodMonitor {

        private long cost;
        private long begin;

        public MethodMonitor(long cost, long begin) {
            this.cost = cost;
            this.begin = begin;
        }

        public long getCost() {
            return cost;
        }

        public void setCost(long cost) {
            this.cost = cost;
        }

        public long getBegin() {
            return begin;
        }

        public void setBegin(long begin) {
            this.begin = begin;
        }
    }
}
