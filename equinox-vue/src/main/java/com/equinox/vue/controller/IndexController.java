package com.equinox.vue.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boreas
 * @create 2023-09-18 16:29
 */
@Slf4j
@RequestMapping(value = "/index")
@Controller
public class IndexController {

    @GetMapping(value = "/search")
    @ResponseBody
    public String searchIndex() {
        return "";
    }
}
