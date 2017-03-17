package service;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CustomerRepository;

import java.util.Collection;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public class CustomerServiceBean implements Service<Customer, Integer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public long size() {
        return 0;
    }

    @Override
    public Customer update(Customer E) {
        return null;
    }

    @Override
    public Customer delete(Integer integer) {
        return null;
    }

    @Override
    public Customer save(Customer E) {
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
        Collection<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer findOne(Integer integer) {
        return null;
    }
}
