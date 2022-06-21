package application.validator;

import application.controller.dto.EventDTO;
import org.springframework.stereotype.Component;

@Component
public class EventValidator implements IValidator<EventDTO> {
    @Override
    public void validate(EventDTO entity) throws ValidatorException {
        String msg = "";

        if ("".equals(entity.getName())) {
            msg += "Event name is invalid!\n";
        }

        if (!"".equals(msg)) {
            throw new ValidatorException(msg);
        }
    }
}
