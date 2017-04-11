package application.controller.converter;

import application.domain.Customer;
import application.model.ResponseCustomer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerToResponse implements IConverter<ResponseCustomer, Customer> {

    @Override
    public ResponseCustomer convert(Customer customer) {

        BetToResponse converter = new BetToResponse();

        ResponseCustomer view = new ResponseCustomer();
        view.setName(customer.getName());
        view.setAccountId(customer.getAccountId());
        view.setBalance(customer.getBalance());
        view.setBets(converter.convert(customer.getBets()));

        return view;
    }

    @Override
    public List<ResponseCustomer> convert(List<Customer> customers) {
        return customers.stream()
                .map(new CustomerToResponse()::convert)
                .collect(Collectors.toList());
    }

}
