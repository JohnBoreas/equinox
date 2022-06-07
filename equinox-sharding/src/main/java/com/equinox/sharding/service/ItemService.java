package com.equinox.sharding.service;

import com.equinox.sharding.entity.ItemEntity;

import java.util.List;

public interface ItemService {

    List<ItemEntity> list();

    Long addItem(ItemEntity item);
}
