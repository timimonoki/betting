package application.service;

import application.validator.ValidatorException;

import java.util.List;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public interface IService<V, K> {

    V update(V entity) throws ValidatorException;
    V delete(K id) throws ValidatorException;
    V create(V entity) throws ValidatorException;
    List findAll();
    V findById(K id) throws ValidatorException;

}
