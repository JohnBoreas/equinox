package com.equinox.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.equinox.sentinel.constants.SentinelConstants;
import com.equinox.sentinel.result.AjaxResult;
import com.equinox.sentinel.service.ItemShardingFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boreas
 * @create 2022-06-09 13:33
 */
@Slf4j
@RequestMapping(value = "/sentinel")
@Controller
public class SentinelController {

    @Autowired
    ItemShardingFeignService itemShardingFeignService;

    /**
     * 代码方式
     * @return
     */
    @GetMapping(value = "/exceptionSentinel")
    // 不在返回视图，解析成json
    @ResponseBody
    public AjaxResult exceptionSentinel() {
        //Entry获取许可
        long threadId = Thread.currentThread().getId();
        Entry entry = null;
        try {
            entry = SphU.entry(SentinelConstants.Resource.EXCEPTION_SENTINEL);
            log.info("exceptionSentinel success---" + threadId);
            return AjaxResult.success("exceptionSentinel success---" + threadId);
        } catch (BlockException e) {
            //如果抛异常了，则表示被限流了
            //e.printStackTrace();
            log.error("当前访问量过大，请稍后重试..." + threadId);
            return AjaxResult.error("当前访问量过大，请稍后重试..." + threadId);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    /**
     * 注解方式
     * @return
     */
    @GetMapping(value = "/annotationSentinel")
    @ResponseBody
    @SentinelResource(value = SentinelConstants.Resource.ANNOTATION_SENTINEL, blockHandler = "blockHandler")
    public AjaxResult annotationSentinel() {
        long threadId = Thread.currentThread().getId();
        log.info("annotationSentinel success---" + threadId);
        return AjaxResult.success("annotationSentinel success---" + threadId);
    }

    public AjaxResult blockHandler(BlockException e) {
        long threadId = Thread.currentThread().getId();
        log.error("当前访问量过大，请稍后重试..." + threadId);
        return AjaxResult.error("当前访问量过大，请稍后重试..." + threadId);
    }

    @RequestMapping(value = "/itemList", method = RequestMethod.GET)
    @SentinelResource(value = SentinelConstants.Resource.ANNOTATION_SENTINEL)
    @ResponseBody
    public AjaxResult itemList(String params) {
        String result = itemShardingFeignService.list();
        return AjaxResult.success(result);
    }


    @GetMapping("/rt")
    @SentinelResource(value = SentinelConstants.Resource.DEGRADE_GRADE_RT)
    @ResponseBody
    public AjaxResult rt() {
        String result = itemShardingFeignService.rt();
        log.error(result);
        return AjaxResult.error(result);
    }

    @GetMapping("/flow")
    @SentinelResource(value = SentinelConstants.Resource.DEGRADE_GRADE_EXCEPTION_RATIO)
    @ResponseBody
    public AjaxResult flow() {
        String result = itemShardingFeignService.flow();
        log.error(result);
        return AjaxResult.error(result);
    }


    @GetMapping("/errorTest")
    @SentinelResource(value = SentinelConstants.Resource.DEGRADE_GRADE_EXCEPTION_COUNT)
    @ResponseBody
    public AjaxResult error() {
        String result = itemShardingFeignService.error();
        log.error(result);
        return AjaxResult.error(result);
    }

    @GetMapping("/thread")
    @ResponseBody
    public AjaxResult thread() {
        String result = itemShardingFeignService.thread();
        log.error(result);
        return AjaxResult.error(result);
    }
}
