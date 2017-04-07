package application.controller.converter;

import application.domain.Bet;
import application.domain.Customer;
import application.model.BetView;
import application.model.CustomerView;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerToView implements IConverter<CustomerView, Customer> {

    @Override
    public CustomerView convert(Customer customer) {

        CustomerView view = new CustomerView();
        view.setName(customer.getName());
        view.setAccountId(customer.getAccountId());
        view.setBalance(customer.getBalance());

        List<Bet> bets = customer.getBets();
        List<BetView> betViewList =
                bets.stream()
                .map(bet -> {
                    BetView betView = new BetView();

                    return betView;
                })
                .collect(Collectors.toList());

        return null;
    }

}
