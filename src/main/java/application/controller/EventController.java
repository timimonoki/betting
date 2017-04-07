package application.controller;

import application.controller.dto.EventDTO;
import application.domain.Event;
import application.service.EventService;
import application.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;
    private EventValidator validator;

    public EventController() {
        validator = new EventValidator();
    }

    private Event converToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());

        return event;
    }

    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public Event addEvent(@RequestBody EventDTO eventDTO) throws Exception {
        validator.validate(eventDTO);

        Event event = converToEvent(eventDTO);

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
    public Event updateEvent(@RequestBody EventDTO eventDTO) throws Exception {
        validator.validate(eventDTO);

        if (eventService.findById(eventDTO.getId()) == null) {
            throw new Exception("Invalid ID!\n");
        }

        Event event = converToEvent(eventDTO);

        return eventService.update(event);
    }

    @RequestMapping(value = "/removeEvent", method = RequestMethod.GET)
    public Event removeEvent(@RequestParam(value = "id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return eventService.delete(id);
    }

    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public List<Event> getEvents(@RequestParam(value = "bets", defaultValue = "false") Boolean withBets) {
        return withBets ?
                eventService.findAll() :
                eventService.findAllEvents();
    }

    @RequestMapping(value = "/findEventsWithMostBets", method = RequestMethod.GET)
    public List<Event> getEventsWithMostBets() {
        return eventService.findEventsWithMostBets();
    }

    @RequestMapping(value = "/findUniqueCustomersOnEventBets", method = RequestMethod.GET)
    public List<String> getUniqueCustomersOnEventBets() {
        return eventService.findUniqueCustomersOnEventBets();
    }
}
