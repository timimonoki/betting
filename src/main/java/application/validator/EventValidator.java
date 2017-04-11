package application.validator;

import application.controller.dto.EventDTO;
import org.springframework.stereotype.Component;

@Component
public class EventValidator implements IValidator<EventDTO> {
    @Override
    public void validate(EventDTO entity) throws ValidatorException {
        String msg = "";

        if (entity.getName().equals("")) {
            msg += "Event name is invalid!\n";
        }

        if (!msg.equals("")) {
            throw new ValidatorException(msg);
        }
    }
}
