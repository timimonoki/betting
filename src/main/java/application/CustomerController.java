package application.controller;

import domain.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CustomerServiceBean;

import java.util.Collection;

/**
 * Created by NegrutiA on 3/17/2017.
 */

@RestController
public class CustomerController {
    private CustomerServiceBean customerService = new CustomerServiceBean();

    @RequestMapping("/getAllCustomers")
    public Collection<Customer> getAllData() {
        return customerService.findAll();
    }
}
