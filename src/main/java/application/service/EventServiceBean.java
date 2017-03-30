package application.service;

import application.domain.Event;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EventServiceBean implements IService<Event, Integer> {

    @Override
    public Event update(Event E) {
        return E;
    }

    @Override
    public Event delete(Integer integer) throws Exception {
        return new Event();
    }

    @Override
    public Event create(Event E) throws Exception {
        return E;
    }

    @Override
    public List<Event> findAll() {
        return Arrays.asList(new Event());
    }

    @Override
    public Event findById(Integer integer) throws Exception {
        return new Event();
    }
}
