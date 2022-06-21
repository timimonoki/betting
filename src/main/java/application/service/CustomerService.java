package application.service;

import application.domain.Customer;
import application.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NegrutiA on 3/16/2017.
 */

@Service
public class CustomerService implements IService<Customer, Integer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer update(Customer entity) {
        Customer customer = customerRepository.getOne(entity.getId());

        customer.setName(entity.getName());
        customer.setAccountId(entity.getAccountId());
        customer.setBalance(entity.getBalance());

        customerRepository.save(customer);

        return customer;
    }

    @Override
    public Customer delete(Integer integer) throws ValidatorException {
        Customer result = customerRepository.findOne(integer);

        if (result == null) {
            throw new ValidatorException("This ID doesn't exist!");
        }

        customerRepository.delete(integer);

        return result;
    }

    @Override
    public Customer create(Customer entity) throws ValidatorException {

        List<Customer> all = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getAccountId().equals(entity.getAccountId()))
                .collect(Collectors.toList());

        if (!all.isEmpty()) {
            throw new ValidatorException("This customer ID already exists!");
        }

        return customerRepository.save(entity);

    }

    public Customer findByAccountId(String accountId) throws ValidatorException {
        List<Customer> result = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getAccountId().equals(accountId))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new ValidatorException("This account dose not exist!");
        }

        return result.get(0);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> customer.setId(null));

        return customers;
    }

    @Override
    public Customer findById(Integer integer) throws ValidatorException {
        Customer result = customerRepository.findOne(integer);

        if (result == null) {
            throw new ValidatorException("This ID doesn't exist!");
        }

        return result;
    }
}
