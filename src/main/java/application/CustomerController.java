package application;

import application.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import application.service.CustomerServiceBean;


/**
 * Created by NegrutiA on 3/17/2017.
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerServiceBean customerService;

    public CustomerController() {}

    @RequestMapping("/addCustomer")
    public String getAllData(@RequestParam(value="accountId", defaultValue = "") String accountId,
                             @RequestParam(value="name", defaultValue = "") String name,
                             @RequestParam(value="balance", defaultValue = "0.0") Double balance) {

        if (!accountId.equals(""))
            customerService.create(new Customer(accountId, name, balance));

        return accountId + " " + name + " " + balance;
    }
}
