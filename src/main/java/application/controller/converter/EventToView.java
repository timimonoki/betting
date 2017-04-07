package application.controller.converter;

import application.domain.Event;
import application.model.EventView;

public class EventToView implements IConverter<EventView, Event> {

    @Override
    public EventView convert(Event event) {
        return null;
    }

}
