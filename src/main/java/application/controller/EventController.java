package application.controller;

import application.controller.converter.EventToResponse;
import application.controller.converter.IConverter;
import application.controller.dto.EventDTO;
import application.domain.Event;
import application.model.ResponseEvent;
import application.service.EventService;
import application.validator.EventValidator;
import application.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private EventService eventService;
    private EventValidator validator;
    private EventToResponse converter;

    @Autowired
    public EventController(EventService eventService, EventValidator validator, EventToResponse converter) {
        this.eventService = eventService;
        this.validator = validator;
        this.converter = converter;
    }

    private Event converDtoToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());

        return event;
    }

    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public ResponseEvent addEvent(@RequestBody EventDTO eventDTO) throws Exception {
        validator.validate(eventDTO);

        Event event = converDtoToEvent(eventDTO);
        Event createdEvent = eventService.create(event);

        return converter.convert(createdEvent);
    }

    @RequestMapping(value = "/getEvent", method = RequestMethod.GET)
    public ResponseEvent getEvent(@RequestParam(value = "name", defaultValue = "") String name) throws Exception {
        if (name.equals("")) {
            throw new Exception("Invalid name!\n");
        }
        Event event = eventService.findByName(name);

        return converter.convert(event);
    }

    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public ResponseEvent updateEvent(@RequestBody EventDTO eventDTO) throws Exception {
        validator.validate(eventDTO);

        Event eventInDb = eventService.findByName(eventDTO.getName());
        if (eventInDb == null) {
            throw new Exception("Invalid name!\n");
        }

        Event event = converDtoToEvent(eventDTO);
        event.setId(eventInDb.getId());
        Event updatedEvent = eventService.update(event);

        return converter.convert(updatedEvent);
    }

    @RequestMapping(value = "/removeEvent", method = RequestMethod.GET)
    public ResponseEvent removeEvent(@RequestParam(value = "name", defaultValue = "") String name) throws Exception {
        if (name.equals("")) {
            throw new Exception("Invalid name!\n");
        }
        Event eventInDb = eventService.findByName(name);
        Event event = eventService.delete(eventInDb.getId());

        return converter.convert(event);
    }

    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public List<Event> getEvents(@RequestParam(value = "bets", defaultValue = "false") Boolean withBets) {
        return withBets ?
                eventService.findAll() :
                eventService.findAllEvents();
    }

    @RequestMapping(value = "/findEventsWithMostBets", method = RequestMethod.GET)
    public List<ResponseEvent> getEventsWithMostBets() {

        List<Event> eventList = eventService.findEventsWithMostBets();

        return converter.convert(eventList);
    }

    @RequestMapping(value = "/findUniqueCustomersOnEventBets", method = RequestMethod.GET)
    public List<String> getUniqueCustomersOnEventBets() {
        return eventService.findUniqueCustomersOnEventBets();
    }
}
