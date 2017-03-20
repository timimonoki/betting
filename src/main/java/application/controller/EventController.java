package application.controller;

import application.domain.Event;
import application.service.EventServiceBean;
import application.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventServiceBean eventService;
    private EventValidator validator;

    public EventController() {
        validator = new EventValidator();
    }

    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public Event addEvent(@RequestBody Event event) throws Exception {
        validator.validate(event);
        return eventService.create(event);
    }

    @RequestMapping(value = "/getEvent", method = RequestMethod.GET)
    public Event getEvent(@RequestParam(value = "id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return eventService.findById(id);
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public Event updateEvent(@RequestBody Event event) throws Exception {
        validator.validate(event);

        if (eventService.findById(event.getId()) == null) {
            throw new Exception("Invalid ID!\n");
        }

        return eventService.update(event);
    }

    @RequestMapping(value = "/removeEvent", method = RequestMethod.POST)
    public Event removeEvent(@RequestParam(value = "id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return eventService.delete(id);
    }

    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public List<Event> getEvents() {
        return eventService.findAll();
    }
}
