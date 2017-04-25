package application.service;

import application.domain.Event;
import application.repository.EventRepository;
import application.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService implements IService<Event, Integer> {

    private static final String MORE_EVENTS = "More than one Event with the specified name!";

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event update(Event entity) throws ValidatorException {
        Event event = eventRepository.getOne(entity.getId());

        List<Event> result = eventRepository.findAll().stream()
                .filter(filterEvent -> filterEvent.getName().compareTo(entity.getName()) == 0)
                .collect(Collectors.toList());

        if (result.size() == 1) {
            throw new ValidatorException("This Event new name already exists!");
        } else if (result.size() > 1) {
            throw new ValidatorException(MORE_EVENTS);
        }

        event.setName(entity.getName());
        event.setBets(entity.getBets());

        eventRepository.save(event);

        return event;
    }

    @Override
    public Event delete(Integer integer) throws ValidatorException {
        Event result = eventRepository.findOne(integer);

        if (result == null) {
            throw new ValidatorException("This ID doesn't exist!");
        }

        eventRepository.delete(integer);

        return result;
    }

    @Override
    public Event create(Event entity) throws ValidatorException {
        List<Event> result = eventRepository.findAll().stream()
                .filter(event -> event.getName().compareTo(entity.getName()) == 0)
                .collect(Collectors.toList());

        if (result.size() == 1) {
            throw new ValidatorException("This Event already exists!");
        } else if (result.size() > 1) {
            throw new ValidatorException(MORE_EVENTS);
        }

        return eventRepository.save(entity);
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

        List<Event> listWithMostBets =
                list
                .stream()
                .filter(event -> event.getBets().size() == mostBets)
                .collect(Collectors.toList());

        listWithMostBets.forEach(EventService::setBetsAndIdsToNull);

        return listWithMostBets;
    }

    /**
     * Finds all the events without the bets.
     * @return List with bets.
     */
    public List<Event> findAllEvents() {
        List<Event> list = eventRepository.findAll();

        list.forEach(EventService::setBetsAndIdsToNull);

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
            bet.getCustomer().setBets(null);
            bet.setEvent(null);
        }));

        return list;
    }

    /**
     * This method finds all the events that have bets associated with and returns the name of the customers
     * for those events that have bets from an unique customer account.
     * @return List with ResponseEvent containing the accountId's.
     */
    public List<String> findUniqueCustomersOnEventBets() {
        List<Event> list = eventRepository.findAll().stream()
                .filter(EventService::eventWithBetsFromUniqueAccount)
                .collect(Collectors.toList());

        return list
                .stream()
                .map(event -> event
                        .getBets()
                        .get(0)
                        .getCustomer()
                        .getAccountId())
                .collect(Collectors.toList());
    }

    @Override
    public Event findById(Integer integer) throws ValidatorException {
        Event result = eventRepository.findOne(integer);

        if (result == null) {
            throw new ValidatorException("This ID dosen't exist!");
        }

        return result;
    }

    public Event findByName(String name) throws ValidatorException {
        List<Event> result = eventRepository.findAll().stream()
                .filter(event -> event.getName().compareTo(name) == 0)
                .collect(Collectors.toList());

        if (result == null || result.isEmpty()) {
            throw new ValidatorException("This Event dose not exist!");
        } else if (result.size() > 1) {
            throw new ValidatorException(MORE_EVENTS);
        }

        return result.get(0);
    }

    private static void setBetsAndIdsToNull(Event event) {
        event.setId(null);
        event.setBets(null);
    }

    /**
     *
     * @param event Event variable
     * @return Returns true if the event has bets from one unique account and false otherwise
     */
    private static boolean eventWithBetsFromUniqueAccount(Event event) {
        Set<String> customers = event.getBets().stream()
                .map(bet -> bet.getCustomer().getAccountId())
                .collect(Collectors.toSet());

        return customers.size() == 1;
    }
}
