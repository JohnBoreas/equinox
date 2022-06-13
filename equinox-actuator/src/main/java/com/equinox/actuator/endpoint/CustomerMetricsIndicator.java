package com.equinox.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "customer")
public class CustomerMetricsIndicator {

    @ReadOperation
    public Map<String,Object> time(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("当前时间",new Date());
        return map;
    }

}
