package com.equinox.jms.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author boreas
 * @create 2023-02-28 11:46
 */
@Data
public class OrderDTO {

    private String itemId;
    private String orderId;

}
