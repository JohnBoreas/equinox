package com.equinox.actuator.jmx;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class JMXMain {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.yuandengta.boot.jmx:type=SystemInfo");
        SystemInfo systemInfo = new SystemInfo();
        //发布
        mBeanServer.registerMBean(systemInfo,objectName);
        //避免main方法结束，阻塞在这里
        System.in.read();
    }
}
