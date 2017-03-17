package application.service;

import application.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NegrutiA on 3/16/2017.
 */

@Service
public class CustomerIServiceBean implements IService<Customer, Integer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer update(Customer E) {
        return null;
    }

    @Override
    public Customer delete(Integer integer) {
        return null;
    }

    @Override
    public Customer create(Customer E) {
        customerRepository.save(E);
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Customer findById(Integer integer) {
        return null;
    }
}
