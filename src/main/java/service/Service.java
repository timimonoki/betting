package service;

import domain.HasID;

import java.util.Collection;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public interface Service<Entity, ID> {

    long size();
    Entity update(Entity E);
    Entity delete(ID id);
    Entity save(Entity E);
    Collection<Entity> findAll();
    Entity findOne(ID id);

}
