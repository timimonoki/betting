package application.model;

import java.util.List;

public class CustomerView {

    private String accountId;

    private String name;

    private Double balance;

    private List<BetView> bets;

    public CustomerView() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<BetView> getBets() {
        return bets;
    }

    public void setBets(List<BetView> bets) {
        this.bets = bets;
    }
}
