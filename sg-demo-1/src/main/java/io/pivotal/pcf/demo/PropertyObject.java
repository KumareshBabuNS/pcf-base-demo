package io.pivotal.pcf.demo;

import java.util.Map;

/**
 * Created by sgupta on 10/1/14.
 */
public class PropertyObject {
    private String key;
    private Map<String,Object> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getValue() {
        return value;
    }

    public void setValue(Map<String, Object> value) {
        this.value = value;
    }
}
