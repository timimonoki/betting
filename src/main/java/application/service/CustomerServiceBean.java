package application.service;

import application.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by NegrutiA on 3/16/2017.
 */

@Service
public class CustomerServiceBean implements IService<Customer, Integer> {

    @Override
    public Customer update(Customer E) {
        return E;
    }

    @Override
    public Customer delete(Integer integer) throws Exception{
        return new Customer();
    }

    @Override
    public Customer create(Customer E) throws Exception {
        return E;

    }

    public Customer findByAccountId(String accountId) throws Exception {
        return new Customer();
    }

    @Override
    public List<Customer> findAll() {
        return Arrays.asList(new Customer());
    }

    @Override
    public Customer findById(Integer integer) throws Exception {
        return new Customer();
    }
}
