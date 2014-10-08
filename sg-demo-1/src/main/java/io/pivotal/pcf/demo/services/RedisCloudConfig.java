package io.pivotal.pcf.demo.services;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Created by sgupta on 10/1/14.
 */
//@Configuration
//@Profile("redis-cloud")
public class RedisCloudConfig extends AbstractCloudConfig {
/*

    @Bean
    public RedisConnectionFactory redisConnection() {
        return connectionFactory().redisConnectionFactory();
    }
*/

}
