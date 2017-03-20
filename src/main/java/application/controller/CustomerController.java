package application.controller;

import application.domain.Customer;
import application.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.service.CustomerServiceBean;
import java.util.List;


/**
 * Created by NegrutiA on 3/17/2017.
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerServiceBean customerService;
    private CustomerValidator validator;

    public CustomerController() {
        validator = new CustomerValidator();
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public Customer addNewCustomer(@RequestBody Customer customer) throws Exception {
        validator.validate(customer);
        return customerService.create(customer);
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam(value="id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return customerService.findById(id);
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }
}
