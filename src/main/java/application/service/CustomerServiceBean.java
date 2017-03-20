package application.service;

import application.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NegrutiA on 3/16/2017.
 */

@Service
public class CustomerServiceBean implements IService<Customer, Integer> {

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
    public Customer create(Customer E) throws Exception {
        List<Customer> all = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getAccountId().equals(E.getAccountId()))
                .collect(Collectors.toList());

        if (!all.isEmpty()) {
            throw new Exception("This customer ID already exists!");
        }

        return customerRepository.save(E);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer integer) throws Exception {
        Customer result;

        try {
            result = customerRepository.getOne(integer);
        }
        catch (EntityNotFoundException exc) {
            throw new Exception("There is no customer with this id!");
        }

        return result;
    }
}
