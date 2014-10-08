package io.pivotal.pcf.demo.controllers;

import io.pivotal.pcf.demo.PropertyObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sgupta on 10/1/14.
 */
@RestController
@RequestMapping(value = "/admin/**", produces = "application/json")
public class AdminController {

    @RequestMapping(value = "/harakiri", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        System.exit(0);
        return "ok";
    }


}
