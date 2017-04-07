package application.model;

import java.util.List;

public class EventView {

    private String name;

    private List<BetView> bets;

    public EventView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BetView> getBets() {
        return bets;
    }

    public void setBets(List<BetView> bets) {
        this.bets = bets;
    }
}
