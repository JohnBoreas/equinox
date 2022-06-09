package com.equinox.sentinel.controller;

import com.equinox.sentinel.result.AjaxResult;
import com.equinox.sentinel.service.ItemShardingFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hystrix
 */
@RestController
@RequestMapping(value = "/hystrix")
@Slf4j
public class HystrixController {

    @Autowired
    ItemShardingFeignService itemShardingFeignService;
    /**
     * 使用线程池限流
     * @return
     */
    @RequestMapping(value = "/theadStrategy", method = RequestMethod.GET)
    @ResponseBody
    @HystrixCommand(fallbackMethod = "flowError",
            commandProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD"),
                    //线程超时时间
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "1000"),
                    //设置超时的时候是否中断线程
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "false")
            }
    )
    public AjaxResult theadStrategy() {
        try {
            log.info(Thread.currentThread().getId() + "：theadStrategy执行休眠前");
            Thread.sleep(2000);
            log.info(Thread.currentThread().getId() + "：theadStrategy执行休眠后");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return AjaxResult.success("ok");
    }

    /**
     * 使用信号量限流
     */
    @RequestMapping(value = "/semaphoreStrategy", method = RequestMethod.GET)
    @ResponseBody
    @HystrixCommand(fallbackMethod = "flowError",
            commandProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
                    // 线程超时时间
//                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "5000"),
                    // 最大并发数，qps，每秒并发数
                    @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "3")
            }
    )
    public AjaxResult semaphoreStrategy() {
        try {
            log.info(Thread.currentThread().getId() + "：semaphoreStrategy执行休眠前");
            Thread.sleep(1000);
            log.info(Thread.currentThread().getId() + "：semaphoreStrategy执行休眠后");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return AjaxResult.success("ok");
    }

    @RequestMapping(value = "/itemList", method = RequestMethod.GET)
    public String itemList(String params) {
        String result = itemShardingFeignService.list();
        return result;
    }

    /**
     * fallbackMethod
     * @return
     */
    public AjaxResult flowError(){
        log.info("当前访问人数过多，请稍后重试");
        return AjaxResult.success("当前访问人数过多，请稍后重试");
    }
}
