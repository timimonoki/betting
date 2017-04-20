package application.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NegrutiA on 3/15/2017.
 */

@Entity
@Table(name = "events")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event implements Serializable, HasID<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "event")
    @JsonManagedReference
    private List<Bet> bets;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
        if (bets != null) {
            List<Bet> clone = new ArrayList<>(bets.size());
            for (Bet item : bets) {
                clone.add(new Bet(item));
            }
            return clone;
        }
        return new ArrayList<>();
    }

    public void setBets(List<Bet> bets) {
        if (bets == null) {
            this.bets = new ArrayList<>();
        } else {
            List<Bet> clone = new ArrayList<>(bets.size());
            for (Bet item : bets) {
                clone.add(new Bet(item));
            }
            this.bets = clone;
        }
    }
}
