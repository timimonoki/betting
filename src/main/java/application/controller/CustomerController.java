package application.controller;

import application.controller.dto.CustomerDTO;
import application.domain.Customer;
import application.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.service.CustomerService;
import java.util.List;


/**
 * Created by NegrutiA on 3/16/2017.
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private CustomerValidator validator;

    public CustomerController() {
        validator = new CustomerValidator();
    }

    private Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setAccountId(customerDTO.getAccountId());
        customer.setName(customerDTO.getName());
        customer.setBalance(customerDTO.getBalance());

        return customer;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        validator.validate(customerDTO);

        Customer customer = convertToCustomer(customerDTO);

        return customerService.create(customer);
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public Customer getCustomer(@RequestParam(value="id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return customerService.findById(id);
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public Customer updateCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        validator.validate(customerDTO);

        if (customerService.findById(customerDTO.getId()) == null) {
            throw new Exception("Invalid ID!\n");
        }

        Customer customer = convertToCustomer(customerDTO);

        return customerService.update(customer);
    }

    @RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
    public Customer removeCustomer(@RequestParam(value="id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }

        return customerService.delete(id);
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }
}
