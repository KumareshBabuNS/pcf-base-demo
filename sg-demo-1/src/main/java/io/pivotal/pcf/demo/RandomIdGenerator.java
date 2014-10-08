package io.pivotal.pcf.demo;

import java.util.Random;
import java.util.UUID;

/**
 * Created by sgupta on 10/1/14.
 */
public class RandomIdGenerator {
    public static final Random RANDOM = new Random();

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
