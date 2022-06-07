package com.equinox.sharding.service.impl;

import com.equinox.sharding.dao.ItemDao;
import com.equinox.sharding.entity.ItemEntity;
import com.equinox.sharding.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author boreas
 * @create 2022-06-07 14:46
 */
@Service
public class ItemServiceImpl implements ItemService {

    public ItemDao dao;

    @Autowired
    public ItemServiceImpl(ItemDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ItemEntity> list() {
        return dao.list();
    }

    @Override
    public Long addItem(ItemEntity item) {
        return dao.addItem(item);
    }
}
