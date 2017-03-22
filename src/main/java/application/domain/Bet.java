package application.domain;

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

    private Integer eventId;
    private String accountId;
    private Double stake;
    private Long betcode;

    public Bet() {}

    public Bet(Integer id, Integer eventId, String accountId, Double stake, Long betcode) {
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
