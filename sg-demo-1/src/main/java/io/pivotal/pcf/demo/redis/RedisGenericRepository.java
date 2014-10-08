package io.pivotal.pcf.demo.redis;

import io.pivotal.pcf.demo.PropertyObject;
import io.pivotal.pcf.demo.RandomIdGenerator;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sgupta on 10/1/14.
 */
public class RedisGenericRepository implements GenericRepository {
    public static final String PROPERTIES_KEY = "props";

    private final RandomIdGenerator idGenerator;
    private final HashOperations<String, String, PropertyObject> hashOps;

    public RedisGenericRepository(RedisTemplate<String, PropertyObject> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
        idGenerator = new RandomIdGenerator();
    }

    @Override
    public <S extends PropertyObject> S save(S propertyObject) {
        if(propertyObject.getKey() == null) {
            propertyObject.setKey(idGenerator.generateId());
        }
        hashOps.put(PROPERTIES_KEY, propertyObject.getKey(), propertyObject);
        return propertyObject;
    }

    @Override
    public <S extends PropertyObject> Iterable<S> save(Iterable<S> propertyObjects) {
        List<S> result = new ArrayList<S>();

        for (S entity : propertyObjects) {
            save(entity);
            result.add(entity);
        }

        return result;
    }

    @Override
    public PropertyObject findOne(String id) {
        return hashOps.get(PROPERTIES_KEY, id);
    }

    @Override
    public boolean exists(String id) {
        return hashOps.hasKey(PROPERTIES_KEY, id);
    }

    @Override
    public Iterable<PropertyObject> findAll() {
        return hashOps.values(PROPERTIES_KEY);
    }

    @Override
    public Iterable<PropertyObject> findAll(Iterable<String> ids) {
        return hashOps.multiGet(PROPERTIES_KEY, convertIterableToList(ids));
    }

    @Override
    public long count() {
        return hashOps.keys(PROPERTIES_KEY).size();
    }

    @Override
    public void delete(String id) {
        hashOps.delete(PROPERTIES_KEY, id);
    }

    @Override
    public void delete(PropertyObject o) {
        hashOps.delete(PROPERTIES_KEY, o.getKey());
    }

    @Override
    public void delete(Iterable<? extends PropertyObject> objects) {
        for (PropertyObject object : objects) {
            delete(object);
        }
    }

    @Override
    public void deleteAll() {
        Set<String> ids = hashOps.keys(PROPERTIES_KEY);
        for (String id : ids) {
            delete(id);
        }
    }

    private <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<T>();
        for (T object : iterable) {
            list.add(object);
        }
        return list;
    }
}
