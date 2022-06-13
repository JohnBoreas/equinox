package com.equinox.actuator.jmx;

/**
 * 系统信息接口
 */
public interface SystemInfoMBean {

    //获取当前CPU核心数
    int getCpuCore();

    //获取内存
    long getTotalMermory();

    void shutdown();

}
