package application.controller.converter;

import application.domain.Event;
import application.model.ResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventToResponse implements IConverter<ResponseEvent, Event> {

    private BetToResponse converter;

    @Autowired
    public EventToResponse(BetToResponse converter) {this.converter = converter;}

    @Override
    public ResponseEvent convert(Event event) {

        ResponseEvent view = new ResponseEvent();
        view.setName(event.getName());
        if (event.getBets() != null) {
            view.setBets(converter.convert(event.getBets()));
        }
        return view;
    }

    @Override
    public List<ResponseEvent> convert(List<Event> events) {
        return events.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
