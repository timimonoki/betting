package application.model;

public class BetView {

    private EventView event;

    private CustomerView customer;

    private Double stake;

    private Long betcode;

    public BetView() {
    }

    public EventView getEvent() {
        return event;
    }

    public void setEvent(EventView event) {
        this.event = event;
    }

    public CustomerView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerView customer) {
        this.customer = customer;
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
