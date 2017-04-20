package application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NegrutiA on 3/14/2017.
 */

@Entity
@Table(name = "customers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable,HasID<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    private String name;

    private Double balance;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Bet> bets;

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

    public List<Bet> getBets() {
        if (bets != null) {
            List<Bet> clone = new ArrayList<>(bets.size());
            for (Bet item : bets) {
                clone.add(new Bet(item));
            }
            return clone;
        }
        return new ArrayList<>();
    }

    public void setBets(List<Bet> bets) {
        if (bets == null) {
            this.bets = new ArrayList<>();
        } else {
            List<Bet> clone = new ArrayList<>(bets.size());
            for (Bet item : bets) {
                clone.add(new Bet(item));
            }
            this.bets = clone;
        }
    }
}
