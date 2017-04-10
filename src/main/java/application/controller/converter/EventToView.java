package application.controller.converter;

import application.domain.Event;
import application.model.ResponseEvent;

public class EventToView implements IConverter<ResponseEvent, Event> {

    @Override
    public ResponseEvent convert(Event event) {

        BetToView converter = new BetToView();

        ResponseEvent view = new ResponseEvent();
        view.setName(event.getName());
        view.setBets(converter.convert(event.getBets()));

        return view;
    }

}
