package application;

import domain.Customer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.CustomerServiceBean;

import java.util.Collection;

/**
 * Created by NegrutiA on 3/17/2017.
 */

@RestController
public class CustomerController {
    private CustomerServiceBean customerService = new CustomerServiceBean();

    @RequestMapping("/addCustomer")
    public String getAllData(@RequestParam(value="accountId", defaultValue = "") String accountId,
                             @RequestParam(value="name", defaultValue = "") String name,
                             @RequestParam(value="balance", defaultValue = "0.0") Double balance) {

        return accountId + " " + name + " " + balance;
    }
}
