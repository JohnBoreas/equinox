package com.equinox.zookeeper.bean;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;

import java.util.List;

/**
 * @author boreas
 * @create 2022-08-22 17:16
 */
public class ZookeeperParam {

    List<ACL> zooDefs;
    CreateMode createMode;
    String path;
    String data;

    public List<ACL> getZooDefs() {
        return zooDefs;
    }

    public void setZooDefs(List<ACL> zooDefs) {
        this.zooDefs = zooDefs;
    }

    public CreateMode getCreateMode() {
        return createMode;
    }

    public void setCreateMode(CreateMode createMode) {
        this.createMode = createMode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
