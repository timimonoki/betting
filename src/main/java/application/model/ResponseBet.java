package application.model;

public class ResponseBet {

    private String eventName;

    private String accountId;

    private String accountName;

    private Double stake;

    private Long betcode;

    public ResponseBet() {}

    public ResponseBet(ResponseBet bet) {
        this.eventName = bet.getEventName();
        this.accountId = bet.getAccountId();
        this.accountName = bet.getAccountName();
        this.stake = bet.getStake();
        this.betcode = bet.getBetcode();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public Long getBetcode() {
        return betcode;
    }

    public void setBetcode(Long betcode) {
        this.betcode = betcode;
    }

}
