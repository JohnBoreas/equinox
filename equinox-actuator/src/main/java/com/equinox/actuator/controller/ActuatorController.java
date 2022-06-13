package com.equinox.actuator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author boreas
 * @create 2022-06-13 15:42
 */
@Slf4j
@RestController
@RequestMapping("/actuator")
public class ActuatorController {

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find() {
        return "qqqq";
    }
}
