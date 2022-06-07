package com.equinox.sharding.entity;

import java.util.Date;

/**
 * @author boreas
 * @create 2022-06-07 14:35
 */
public class ItemEntity {

    private static final long serialVersionUID = -1205226416664488559L;

    private Long itemId;
    private String title;
    private Date createTime;
    private Date updateTime;


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "itemId='" + itemId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
