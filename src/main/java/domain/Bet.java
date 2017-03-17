package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by NegrutiA on 3/14/2017.
 */

@Entity
@Table(name = "bets")
public class Bet implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer eventId;
    private String accountId;
    private Double stake;

    @Id
    private Integer betcode;

    public Bet() {}

    public Bet(Integer id, Integer eventId, String accountId, Double stake, Integer betcode) {
        this.id = id;
        this.eventId = eventId;
        this.accountId = accountId;
        this.stake = stake;
        this.betcode = betcode;
    }

    public Bet(Integer eventId, String accountId, Double stake) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.stake = stake;
    }

    @Column ( unique = true, nullable = false )
    public Integer getBetcode() {
        return betcode;
    }

    public void setBetcode(Integer betcode) {
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

    @Column( nullable = false )
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Column( nullable = false )
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Column (columnDefinition = "UNSIGNED DOUBLE(11)")
    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }
}
