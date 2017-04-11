package application.controller.converter;

import application.domain.Customer;
import application.model.ResponseCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerToResponse implements IConverter<ResponseCustomer, Customer> {

    private BetToResponse converter;

    @Autowired
    public CustomerToResponse(BetToResponse converter) {
        this.converter = converter;
    }

    @Override
    public ResponseCustomer convert(Customer customer) {

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
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
