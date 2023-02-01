package com.equinox.zookeeper.controller;

import com.alibaba.fastjson.JSON;
import com.equinox.zookeeper.bean.ZookeeperParam;
import com.equinox.zookeeper.service.ZookeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author boreas
 * @create 2022-08-22 17:20
 */
@Slf4j
@RequestMapping(value = "/zookeeper")
@RestController
public class ZookeeperController {

    private ZookeeperService zookeeperService;

    @Autowired
    public ZookeeperController(ZookeeperService zookeeperService) {
        this.zookeeperService = zookeeperService;
    }

    @RequestMapping(value = "/createNode", method = RequestMethod.GET)
    public String createNode(HttpServletRequest request) {
//        String zooDefs = request.getParameter("zooDefs");
        String createMode = request.getParameter("createMode");
        String path = request.getParameter("nodePath");
        String data = request.getParameter("nodeData");
        ZookeeperParam param = new ZookeeperParam();
        param.setPath(path);
        param.setData(data);
        if (StringUtils.isNotEmpty(createMode)) {
            try {
                param.setCreateMode(CreateMode.fromFlag(Integer.parseInt(createMode)));
            } catch (KeeperException e) {
                log.error(e.getMessage(), e);
                return e.getMessage();
            }
        }
        param.setZooDefs(ZooDefs.Ids.OPEN_ACL_UNSAFE);
        return zookeeperService.createNode(param);
    }

    @RequestMapping(value = "/deleteNode", method = RequestMethod.GET)
    public String deleteNode(HttpServletRequest request) {
        String path = request.getParameter("nodePath");
        ZookeeperParam param = new ZookeeperParam();
        param.setPath(path);
        return zookeeperService.deleteNode(param);
    }

    @RequestMapping(value = "/getNodeData", method = RequestMethod.GET)
    public String getNodeData(HttpServletRequest request) {
        String path = request.getParameter("nodePath");
        ZookeeperParam param = new ZookeeperParam();
        param.setPath(path);
        return zookeeperService.getNodeData(param);
    }

    @RequestMapping(value = "/getChildrenNode", method = RequestMethod.GET)
    public String getChildrenNode(HttpServletRequest request) {
        String path = request.getParameter("nodePath");
        ZookeeperParam param = new ZookeeperParam();
        param.setPath(path);
        return JSON.toJSONString(zookeeperService.getChildrenNode(param));
    }
}
