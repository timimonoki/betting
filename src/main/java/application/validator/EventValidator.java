package application.validator;

import application.domain.Event;

public class EventValidator implements IValidator<Event> {
    @Override
    public void validate(Event entity) throws ValidatorException {
        String msg = "";

        if (entity.getName().equals("")) {
            msg += "Event name is invalid!\n";
        }

        if (!msg.equals("")) {
            throw new ValidatorException(msg);
        }
    }
}
