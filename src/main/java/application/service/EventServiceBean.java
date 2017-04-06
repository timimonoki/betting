package application.service;

import application.domain.Event;
import application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    /**
     * Finds the events that have the most bets associated with.
     * @return List with bets.
     */
    public List<Event> findEventsWithMostBets() {
        List<Event> list = eventRepository.findAll();

        final Comparator<Event> comp = (e1, e2) -> ((Integer) e1.getBets().size()).compareTo(e2.getBets().size());

        int mostBets = list
                .stream()
                .max(comp)
                .orElse(null)
                .getBets()
                .size();

        List<Event> list1 =
                list
                .stream()
                .filter(event -> event.getBets().size() == mostBets)
                .collect(Collectors.toList());

        list1.forEach(event -> {
            event.setBets(null);
            event.setId(null);
        });

        return list1;
    }

    /**
     * Finds all the events without the bets.
     * @return List with bets.
     */
    public List<Event> findAllEvents() {
        List<Event> list = eventRepository.findAll();

        list.forEach(event -> event.setId(null));
        list.forEach(event -> event.setBets(null));

        return list;
    }

    /**
     * Finds all the events. Bets for the events are included.
     * @return List with bets.
     */
    @Override
    public List<Event> findAll() {
        List<Event> list = eventRepository.findAll();

        list.forEach(event -> event.setId(null));
        list.forEach(event -> event.getBets().forEach(bet -> {
            bet.setId(null);
            bet.getCustomer().setId(null);
        }));

        return list;
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
