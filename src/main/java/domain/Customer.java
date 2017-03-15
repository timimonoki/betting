package domain;

/**
 * Created by NegrutiA on 3/14/2017.
 */
public class Customer {
    private String accountId;
    private String name;
    private Double balance;

    public Customer() {}

    public Customer(String accountId, String name, Double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
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
}
