package domain;

/**
 * Created by NegrutiA on 3/14/2017.
 */
public class Bet {
    private Integer id;
    private Integer eventId;
    private String accountId;
    private Integer stake;
    private Integer betcode;

    public Bet() {}

    public Bet(Integer id, Integer eventId, String accountId, Integer stake, Integer betcode) {
        this.id = id;
        this.eventId = eventId;
        this.accountId = accountId;
        this.stake = stake;
        this.betcode = betcode;
    }

    public Bet(Integer eventId, String accountId, Integer stake) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.stake = stake;
    }

    public Integer getBetcode() {
        return betcode;
    }

    public void setBetcode(Integer betcode) {
        this.betcode = betcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStake() {
        return stake;
    }

    public void setStake(Integer stake) {
        this.stake = stake;
    }
}
