package application.model;

import java.util.List;

public class ResponseEvent {

    private String name;

    private List<ResponseBet> bets;

    public ResponseEvent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponseBet> getBets() {
        return bets;
    }

    public void setBets(List<ResponseBet> bets) {
        this.bets = bets;
    }
}
