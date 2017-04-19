package application.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseEvent {

    private String name;

    private List<ResponseBet> bets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResponseBet> getBets() {
        List<ResponseBet> clone = new ArrayList<>(bets.size());
        for (ResponseBet item : bets) {
            clone.add(new ResponseBet(item));
        }
        return clone;
    }

    public void setBets(List<ResponseBet> bets) {
        if (bets == null) {
            this.bets = new ArrayList<>();
        } else {
            List<ResponseBet> clone = new ArrayList<>(bets.size());
            for (ResponseBet item : bets) {
                clone.add(new ResponseBet(item));
            }
            this.bets = clone;
        }
    }
}
