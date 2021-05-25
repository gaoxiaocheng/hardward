package com.core.utils;

/**
 * 每个线程维护一个系统编码(即角色编码),用于查询简称，生成系统简称+UUID
 */
public class SystemContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getSystemCode(){
        return contextHolder.get();
    }

    public static void setSystemCode(String systemCode){
        contextHolder.set(systemCode);
    }

    public static void clear(){
        contextHolder.remove();
    }
}
