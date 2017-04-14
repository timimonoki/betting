package application.controller.dto;

import java.io.Serializable;

public class EventDTO implements Serializable {

    private String name;
    private String newName;

    public EventDTO() {}

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
