package com.equinox.sharding.controller;

import com.equinox.sharding.entity.ItemEntity;
import com.equinox.sharding.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping(value = "/item")
@RestController
public class ItemController {

    public ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ItemEntity> list(HttpServletRequest request) {
        List<ItemEntity> list = service.list();
        log.info(String.valueOf(list.size()));
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request) {
        String title = request.getParameter("title");
        String itemId = request.getParameter("itemId");
        ItemEntity entity = new ItemEntity();
        entity.setItemId(Long.parseLong(itemId));
        entity.setTitle(title);
        Long s = service.addItem(entity);
        String result = "storage success，返回参数：" + "name=" + title + "----itemId=" + itemId + "_" + s;
        return result;
    }

}
