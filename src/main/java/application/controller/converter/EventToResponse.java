package application.controller.converter;

import application.domain.Event;
import application.model.ResponseEvent;

import java.util.List;
import java.util.stream.Collectors;

public class EventToResponse implements IConverter<ResponseEvent, Event> {

    @Override
    public ResponseEvent convert(Event event) {

        BetToResponse converter = new BetToResponse();

        ResponseEvent view = new ResponseEvent();
        view.setName(event.getName());
        view.setBets(converter.convert(event.getBets()));

        return view;
    }

    @Override
    public List<ResponseEvent> convert(List<Event> events) {
        return events.stream()
                .map(new EventToResponse()::convert)
                .collect(Collectors.toList());
    }

}
