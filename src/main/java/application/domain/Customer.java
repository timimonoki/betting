package application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by NegrutiA on 3/14/2017.
 */

@Entity
@Table(name = "customers")
public class Customer implements Serializable,HasID<Integer> {
    @Id
    private Integer id;

    private String accountId;

    private String name;

    private Double balance;

    public Customer() {}

    public Customer(String accountId, String name, Double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public Customer(Integer id, String accountId, String name, Double balance) {
        this.id = id;
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

    @Column( nullable = false )
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "UNSIGNED DOUBLE(11)")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer newId) {
        this.id = newId;
    }
}
