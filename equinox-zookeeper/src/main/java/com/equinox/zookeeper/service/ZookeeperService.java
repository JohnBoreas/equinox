package com.equinox.zookeeper.service;

import com.equinox.zookeeper.bean.ZookeeperParam;

import java.util.List;

/**
 * @author boreas
 * @create 2022-08-22 16:23
 */
public interface ZookeeperService {

    String createNode(ZookeeperParam param);

    String deleteNode(ZookeeperParam param);

    String getNodeData(ZookeeperParam param);

    List<String> getChildrenNode(ZookeeperParam param);
}
