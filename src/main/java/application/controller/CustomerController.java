package application.controller;

import application.controller.converter.CustomerToResponse;
import application.controller.converter.IConverter;
import application.controller.dto.CustomerDTO;
import application.domain.Customer;
import application.model.ResponseCustomer;
import application.validator.CustomerValidator;
import application.validator.IValidator;
import application.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import application.service.CustomerService;
import java.util.List;


/**
 * Created by NegrutiA on 3/16/2017.
 */

@RestController
public class CustomerController {

    private static final String INVALID_NAME = "Invalid name!\n";

    private CustomerService customerService;
    private IValidator<CustomerDTO> validator;
    private IConverter<ResponseCustomer, Customer> converter;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerValidator validator, CustomerToResponse converter) {
        this.customerService = customerService;
        this.validator = validator;
        this.converter = converter;
    }

    private Customer convertDtoToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setAccountId(customerDTO.getAccountId());
        customer.setName(customerDTO.getName());
        customer.setBalance(customerDTO.getBalance());

        return customer;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public ResponseCustomer addCustomer(@RequestBody CustomerDTO customerDTO) throws ValidatorException {
        validator.validate(customerDTO);

        Customer customer = convertDtoToCustomer(customerDTO);
        Customer createdCustomer = customerService.create(customer);

        return converter.convert(createdCustomer);
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public ResponseCustomer getCustomer(@RequestParam(value="accountId", defaultValue = "") String name) throws ValidatorException {
        if ("".equals(name)) {
            throw new ValidatorException(INVALID_NAME);
        }
        Customer customerInDb = customerService.findByAccountId(name);

        Customer customer = customerService.findById(customerInDb.getId());

        return converter.convert(customer);
    }

    @RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
    public ResponseCustomer updateCustomer(@RequestBody CustomerDTO customerDTO) throws ValidatorException {
        validator.validate(customerDTO);

        Customer customerInDto = customerService.findByAccountId(customerDTO.getAccountId());
        if (customerInDto == null) {
            throw new ValidatorException(INVALID_NAME);
        }

        Customer customer = convertDtoToCustomer(customerDTO);
        customer.setId(customerInDto.getId());
        Customer updatedCustomer = customerService.update(customer);

        return converter.convert(updatedCustomer);
    }

    @RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
    public ResponseCustomer removeCustomer(@RequestParam(value="accountId", defaultValue = "") String name) throws ValidatorException {
        if ("".equals(name)) {
            throw new ValidatorException(INVALID_NAME);
        }
        Customer customerInDb = customerService.findByAccountId(name);

        Customer customer = customerService.delete(customerInDb.getId());

        return converter.convert(customer);
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<ResponseCustomer> getCustomers() {

        List<Customer> customerList = customerService.findAll();

        return converter.convert(customerList);
    }
}
