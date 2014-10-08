package io.pivotal.pcf.demo.services;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.io.IOException;

/**
 * Created by sgupta on 10/1/14.
 */
@Configuration
//@Profile("redis-local")
public class RedisLocalConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisLocalConfig.class);

    @Bean
    public RedisConnectionFactory redisConnection() {
        try {
            String vcap_services = System.getenv("VCAP_SERVICES");
            if (vcap_services != null && vcap_services.length() > 0) {
                // parsing rediscloud credentials
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(vcap_services);
                JsonNode rediscloudNode = root.get("rediscloud");
                JsonNode credentials = rediscloudNode.get(0).get("credentials");

                JedisConnectionFactory factory = new JedisConnectionFactory();
                factory.setHostName(credentials.get("hostname").getTextValue());
                factory.setPort(Integer.parseInt(credentials.get("port").getTextValue()));
                factory.setTimeout(Protocol.DEFAULT_TIMEOUT);
                factory.setPassword(credentials.get("password").getTextValue());

                logger.debug("vcap services is: " + vcap_services);
                logger.debug("factory is: " + factory);
                return factory;
/*
                pool = new JedisPool(new JedisPoolConfig(),
                        credentials.get("hostname").getTextValue(),
                        Integer.parseInt(credentials.get("port").getTextValue()),
                        Protocol.DEFAULT_TIMEOUT,
                        credentials.get("password").getTextValue());
*/
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JedisConnectionFactory();
    }

}
