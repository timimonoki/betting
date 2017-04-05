package application.service;

import application.domain.Event;
import application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceBean implements IService<Event, Integer> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event update(Event E) {
        Event event = eventRepository.getOne(E.getId());

        event.setName(E.getName());
        event.setBets(E.getBets());

        eventRepository.save(event);

        return event;
    }

    @Override
    public Event delete(Integer integer) throws Exception {
        Event result = eventRepository.findOne(integer);

        if (result == null) {
            throw new Exception("This ID doesn't exist!");
        }

        eventRepository.delete(integer);

        return result;
    }

    @Override
    public Event create(Event E) throws Exception {
        return eventRepository.save(E);
    }

    public List<Event> findAllEvents() {
        List<Event> list = eventRepository.findAll();
        return list
                .stream()
                .map(event -> {
                    Event e = new Event();
                    e.setId(event.getId());
                    e.setName(event.getName());
                    return e;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Integer integer) throws Exception {
        Event result = eventRepository.findOne(integer);

        if (result == null) {
            throw new Exception("This ID dosen't exist!");
        }

        return result;
    }
}
