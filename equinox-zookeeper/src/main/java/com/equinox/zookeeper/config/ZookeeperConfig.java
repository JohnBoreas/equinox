package com.equinox.zookeeper.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author boreas
 * @create 2022-08-18 15:36
 */
@Configuration
@Slf4j
public class ZookeeperConfig {

    public String host = "192.168.223.5:2181,192.168.223.6:2181,192.168.223.7:2181";

    @Bean
    public CuratorFramework initCuratorFramework() {
        CuratorFramework client = null;
        try {
            //核心参数为流式设置
            client = CuratorFrameworkFactory
                    .builder()
                    .connectString(host)
                    /**
                     重连机制:
                     1、RetryOneTime 后重连一次
                     2、RetryNTimes 重连N次
                     3、RetryForever 一直重连
                     4、RetryUntilElapsed 重连N次，与RetryOneTime区别是不能超过最长等待时间
                     */
                    .retryPolicy(new RetryUntilElapsed(4,1000))
                    .connectionTimeoutMs(4000)
                    .maxCloseWaitMs(6000)
                    .namespace("data")
                    .build();
            client.start();
        } catch (Exception e) {
            log.error("CuratorFramework error", e);
        }
        return client;
    }
//    @Bean
    public ZooKeeper zooKeeper() {
        /**
         客户端可以通过创建⼀个zk实例来连接zk服务器
         new Zookeeper(connectString,sesssionTimeOut,Wather)
         connectString: 连接地址： IP：端⼝
         sesssionTimeOut：会话超时时间：单位毫秒
         Wather：监听器(当特定事件触发监听时， zk会通过watcher通知到客户端)
         **/
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(host, 5000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        log.info("ZooKeeper创建成功");
                        countDownLatch.countDown();
                    }
                }
            });
            log.info("等待ZooKeeper客户端创建");
            countDownLatch.await();
        } catch (IOException e) {
            log.error("ZooKeeper new error", e);
        } catch (InterruptedException e) {
            log.error("ZooKeeper countDownLatch await error", e);
        }
        log.info("ZooKeeper客户端创建完成");
        return zooKeeper;
    }
}
