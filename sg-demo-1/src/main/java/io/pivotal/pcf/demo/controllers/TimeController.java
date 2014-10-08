package io.pivotal.pcf.demo.controllers;

import com.sun.istack.internal.Nullable;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sgupta on 10/1/14.
 */
@RestController
@RequestMapping(value = "/time/**", produces = "application/json")
public class TimeController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> get(@RequestParam("fmt") String format) {
        Map<String,String> response = new HashMap<>();
        long time = System.currentTimeMillis();
        response.put("time", "" + time);

        SimpleDateFormat dateFormat = format == null ? new SimpleDateFormat() : new SimpleDateFormat(format);
        response.put("date", dateFormat.format(new Date(time)));
        return response;
    }


}
