package application.controller.dto;

import java.io.Serializable;

public class EventDTO implements Serializable {

    private String name;

    public EventDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
