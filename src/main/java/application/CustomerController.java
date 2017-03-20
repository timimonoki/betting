package application;

import application.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.service.CustomerServiceBean;


/**
 * Created by NegrutiA on 3/17/2017.
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerServiceBean customerService;

    public CustomerController() {}

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addNewCustomer(@RequestBody Customer customer) {

        if (customer.getName().equals("") ||
                customer.getAccountId().equals("") ||
                customer.getBalance() < 0)
            return "Invalid customer!";
        else {
            customerService.create(customer);
            return "Customer was added!";
        }
    }
}
