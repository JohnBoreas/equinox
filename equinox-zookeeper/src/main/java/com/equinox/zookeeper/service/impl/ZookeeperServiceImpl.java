package com.equinox.zookeeper.service.impl;

import com.alibaba.fastjson.JSON;
import com.equinox.zookeeper.bean.ZookeeperParam;
import com.equinox.zookeeper.service.ZookeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author boreas
 * @create 2022-08-22 17:19
 */
@Service
@Slf4j
public class ZookeeperServiceImpl implements ZookeeperService {

    private CuratorFramework client;

    @Autowired
    public ZookeeperServiceImpl(CuratorFramework client) {
        this.client = client;
    }

    @Override
    public String createNode(ZookeeperParam param) {
        // 父节点不存在则创建
        String path = null;
        try {
            path = client.create()
                    .creatingParentsIfNeeded()
                    //节点类型
                    .withMode(param.getCreateMode())
                    .withACL(param.getZooDefs())
                    .forPath(param.getPath(),
                    param.getData().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("create path error " + param.getPath(), e);
        }
        return path;
    }

    @Override
    public String deleteNode(ZookeeperParam param) {
        // deletingChildrenIfNeeded如果有子节点一并删除
        // guaranteed必须成功比如网络抖动时造成命令失败
        final String[] data = {null};
        try {
            client.delete()
                    .guaranteed()
                    .deletingChildrenIfNeeded()
                    .inBackground(new BackgroundCallback() {
                        @Override
                        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                            System.out.println("删除成功");
                            // { "path":"/javacui/p1","resultCode":0,"type":"DELETE"}
                            String event = JSON.toJSONString(curatorEvent, true);
                            log.info("zookeeper deleteNode删除成功" + event);
                            data[0] = event;
                        }})
                    .forPath(param.getPath());
        } catch (Exception e) {
            log.error("deleteNode error " + param.getPath(), e);
        }
        return data[0];
    }

    @Override
    public String getNodeData(ZookeeperParam param) {
        // 查内容
        byte[] data = new byte[0];
        try {
            data = client.getData().forPath(param.getPath());
        } catch (Exception e) {
            log.error("getNodeData error " + param.getPath(), e);
            return e.getMessage();
        }
        // 查状态
//        Stat stat = new Stat();
//        client.getData().storingStatIn(stat).forPath("/zkblog/p1");
//        System.out.println(JSON.toJSONString(stat, true));
        return new String(data);
    }

    @Override
    public List<String> getChildrenNode(ZookeeperParam param) {
        List<String> paths = null;
        try {
            paths = client.getChildren().forPath(param.getPath());
        } catch (Exception e) {
            log.error("getChildrenNode error " + param.getPath(), e);
        }
        return paths;
    }
}
