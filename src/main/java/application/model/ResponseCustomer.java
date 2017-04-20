package application.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseCustomer {

    private String accountId;

    private String name;

    private Double balance;

    private List<ResponseBet> bets;

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

    public List<ResponseBet> getBets() {
        if (bets != null) {
            List<ResponseBet> clone = new ArrayList<>(bets.size());
            for (ResponseBet item : bets) {
                clone.add(new ResponseBet(item));
            }
            return clone;
        }
        return new ArrayList<>();
    }

    public void setBets(List<ResponseBet> bets) {
        if (bets == null) {
            this.bets = new ArrayList<>();
        } else {
            List<ResponseBet> clone = new ArrayList<>(bets.size());
            for (ResponseBet item : bets) {
                clone.add(new ResponseBet(item));
            }
            this.bets = clone;
        }
    }
}
