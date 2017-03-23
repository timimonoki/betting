package application.controller.dto;

import java.io.Serializable;

public class BetDTO implements Serializable {

    private Integer eventId;
    private String accountId;
    private Double stake;

    public BetDTO() {}

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
