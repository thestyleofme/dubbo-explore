package com.github.thestyleofme.dubbo.loadbalance;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/10/30 4:02
 * @since 1.0.0
 */
public class OnlyFirstLoadbalancer implements LoadBalance {

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        // 所有服务提供者按照ip+port排序取第一个
        Optional<Invoker<T>> optional = invokers.stream().min((o1, o2) -> {
            int ipCompareTo = o1.getUrl().getIp().compareTo(o2.getUrl().getIp());
            if (ipCompareTo == 0) {
                return Integer.compare(o1.getUrl().getPort(), o2.getUrl().getPort());
            }
            return ipCompareTo;
        });
        Invoker<T> invoker = optional.orElseGet(() -> invokers.get(ThreadLocalRandom.current().nextInt(invokers.size())));
        System.out.println("load balance invoke: " + invoker.getUrl().getIp() + ":" + invoker.getUrl().getPort());
        return invoker;
    }
}
