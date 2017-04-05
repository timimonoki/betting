package application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by NegrutiA on 3/14/2017.
 */

@Entity
@Table(name = "bets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bet implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    @JsonBackReference
    private Event event;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    private Double stake;

    @Column ( unique = true, nullable = false )
    private Long betcode;

    public Bet() {}

    public Long getBetcode() {
        return betcode;
    }

    public void setBetcode(Long betcode) {
        this.betcode = betcode;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event eventId) {
        this.event = eventId;
    }

    @Column( nullable = false )
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer accountId) {
        this.customer = accountId;
    }

    @Column (columnDefinition = "UNSIGNED DOUBLE(11)")
    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }
}
