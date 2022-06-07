package com.equinox.sharding.dao;

import com.equinox.sharding.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 */
@Mapper
public interface ItemDao {

    Long addItem(ItemEntity item);

    List<ItemEntity> list();

    void deleteAll();

    List<ItemEntity> findById(int id);

}

