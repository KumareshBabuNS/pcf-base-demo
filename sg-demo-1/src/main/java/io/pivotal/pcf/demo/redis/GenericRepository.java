package io.pivotal.pcf.demo.redis;

import io.pivotal.pcf.demo.PropertyObject;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sgupta on 10/1/14.
 */
public interface GenericRepository extends CrudRepository<PropertyObject,String> {
}
