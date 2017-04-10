package application.controller.converter;

import application.domain.Customer;
import application.model.ResponseCustomer;


public class CustomerToView implements IConverter<ResponseCustomer, Customer> {

    @Override
    public ResponseCustomer convert(Customer customer) {

        BetToView converter = new BetToView();

        ResponseCustomer view = new ResponseCustomer();
        view.setName(customer.getName());
        view.setAccountId(customer.getAccountId());
        view.setBalance(customer.getBalance());
        view.setBets(converter.convert(customer.getBets()));

        return view;
    }

}
