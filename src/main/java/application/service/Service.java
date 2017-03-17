package application.service;

import java.util.List;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public interface Service<Entity, ID> {

    Entity update(Entity E);
    Entity delete(ID id);
    Entity create(Entity E);
    List findAll();
    Entity findById(ID id);

}
