package application.service;

import java.util.List;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public interface IService<Entity, ID> {

    Entity update(Entity E);
    Entity delete(ID id) throws Exception;
    Entity create(Entity E) throws Exception;
    List findAll();
    Entity findById(ID id) throws Exception;

}
