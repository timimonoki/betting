package application.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by NegrutiA on 3/15/2017.
 */

@Entity
@Table(name = "events")
public class Event implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    private List<Bet> bets;

    public Event() {}

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
