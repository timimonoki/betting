package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NegrutiA on 3/15/2017.
 */

@Entity
@Table(name = "events")
public class Event implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany
    private List<Bet> bets;

    public Event() {}

    public Event(Integer id, String name) {
        this.id = id;
        this.name = name;
        bets = new ArrayList<>();
    }

    public Event(Integer id, String name, List<Bet> bets) {
        this.id = id;
        this.name = name;
        this.bets = bets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
