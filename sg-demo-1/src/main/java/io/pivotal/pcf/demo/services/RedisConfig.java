package io.pivotal.pcf.demo.services;

import io.pivotal.pcf.demo.PropertyObject;
import io.pivotal.pcf.demo.redis.RedisGenericRepository;
import org.springframework.cloud.service.keyval.RedisConnectionFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Objects;

/**
 * Created by sgupta on 10/1/14.
 */
@Configuration
//@Profile("redis")
public class RedisConfig {

    @Bean
    public RedisGenericRepository redisRepository(RedisTemplate<String, PropertyObject> redisTemplate) {
        return new RedisGenericRepository(redisTemplate);
    }

    @Bean
    public RedisTemplate<String, PropertyObject> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, PropertyObject> template = new RedisTemplate<String, PropertyObject>();

        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<PropertyObject> objectSerializer = new JacksonJsonRedisSerializer<PropertyObject>(PropertyObject.class);

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(objectSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(objectSerializer);

        return template;
    }

}
