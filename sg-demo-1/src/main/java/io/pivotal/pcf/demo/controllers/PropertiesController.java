package io.pivotal.pcf.demo.controllers;

import io.pivotal.pcf.demo.PropertyObject;
import io.pivotal.pcf.demo.QueryResponse;
import io.pivotal.pcf.demo.RandomIdGenerator;
import io.pivotal.pcf.demo.redis.GenericRepository;
import io.pivotal.pcf.demo.redis.RedisGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by sgupta on 10/1/14.
 */
@RestController
@RequestMapping(value = "/prop/**", produces = "application/json")
public class PropertiesController {
    public static final Logger LOGGER = Logger.getLogger(PropertiesController.class.getName());

    private final RedisGenericRepository repository;


    @Autowired
    public PropertiesController(RedisGenericRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "/put/{key}", method = RequestMethod.POST)
    @ResponseBody
    public PropertyObject put(@PathVariable String key, @RequestBody Map<String,Object> payload) {
        LOGGER.info(">> put key: " + key);
        PropertyObject propertyObject = new PropertyObject();
        propertyObject.setKey(key);
        propertyObject.setValue(payload);
        return repository.save(propertyObject);
    }

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @ResponseBody
    public PropertyObject put(@RequestBody Map<String,Object> payload) {
        PropertyObject propertyObject = new PropertyObject();
        propertyObject.setValue(payload);
        return repository.save(propertyObject);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseBody
    public PropertyObject get(@PathVariable String key) {
        return repository.findOne(key);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<PropertyObject> get() {
        return repository.findAll();
    }




}
