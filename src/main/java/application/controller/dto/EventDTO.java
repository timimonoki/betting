package application.controller.dto;

import java.io.Serializable;

public class EventDTO implements Serializable {

    private Integer id;
    private String name;

    public EventDTO() {}

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
}
