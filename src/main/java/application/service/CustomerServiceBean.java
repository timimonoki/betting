package application.service;

import application.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

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
        Customer c = customerRepository.getOne(E.getId());

        c.setName(E.getName());
        c.setAccountId(E.getAccountId());
        c.setBalance(E.getBalance());

        customerRepository.save(c);

        return c;
    }

    @Override
    public Customer delete(Integer integer) throws Exception{
        Customer result = customerRepository.findOne(integer);

        if (result == null) {
            throw new Exception("This ID doesn't exist!");
        }

        customerRepository.delete(integer);

        return result;
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

    public Customer findByAccountId(String accountId) throws Exception {
        List<Customer> result = customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getAccountId().equals(accountId))
                .collect(Collectors.toList());

        if (result.size() < 1) {
            throw new Exception("This account dose not exist!");
        }

        return result.get(0);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer integer) throws Exception {
        Customer result = customerRepository.findOne(integer);

        if (result == null) {
            throw new Exception("This ID doesn't exist!");
        }

        return result;
    }
}
