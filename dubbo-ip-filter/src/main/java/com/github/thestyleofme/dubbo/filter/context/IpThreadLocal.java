package com.github.thestyleofme.dubbo.filter.context;

/**
 * <p>
 * description
 * </p>
 *
 * @author isaac 2020/11/08 22:10
 * @since 1.0.0
 */
public class IpThreadLocal {

    private IpThreadLocal(){

    }

    private static final ThreadLocal<String> IP_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void setIp(String ip){
        IP_THREAD_LOCAL.set(ip);
    }

    public static String getIp(){
        return IP_THREAD_LOCAL.get();
    }

    public static void clear(){
        IP_THREAD_LOCAL.remove();
    }
}
