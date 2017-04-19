package application.service;

import application.validator.ValidatorException;

import java.util.List;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public interface IService<E, K> {

    E update(E entity) throws ValidatorException;
    E delete(K id) throws ValidatorException;
    E create(E entity) throws ValidatorException;
    List findAll();
    E findById(K id) throws ValidatorException;

}