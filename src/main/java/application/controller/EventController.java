package application.controller;

import application.controller.dto.EventDTO;
import application.domain.Event;
import application.service.EventServiceBean;
import application.validator.EventValidator;
import application.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventServiceBean eventService;
    private EventValidator validator;

    public EventController() {
        validator = new EventValidator();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/addEvent", method = RequestMethod.POST)
    public Event addEvent(@RequestBody EventDTO eventDTO) throws Exception {
        validator.validate(eventDTO);

        Event event = new Event();
        event.setName(eventDTO.getName());

        return eventService.create(event);
    }

    @RequestMapping(value = "/getEvent", method = RequestMethod.GET)
    public Event getEvent(@RequestParam(value = "id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return eventService.findById(id);
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
    public Event updateEvent(@RequestBody EventDTO eventDTO) throws Exception {
        validator.validate(eventDTO);

        if (eventService.findById(eventDTO.getId()) == null) {
            throw new Exception("Invalid ID!\n");
        }

        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());

        return eventService.update(event);
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/removeEvent", method = RequestMethod.GET)
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
