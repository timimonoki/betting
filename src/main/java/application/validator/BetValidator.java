package application.validator;

import application.controller.dto.BetDTO;
import org.springframework.stereotype.Component;

@Component
public class BetValidator implements IValidator<BetDTO> {
    @Override
    public void validate(BetDTO entity) throws ValidatorException {
        String msg = "";

        if (entity.getEventId() < 0) {
            msg += "Event ID is invalid!\n";
        }
        if (entity.getAccountId() == null
                ||entity.getAccountId().equals("")) {
            msg += "Customer ID is invalid!\n";
        }
        //Check if there are any whitespaces
        else if (!(entity.getAccountId().length() == entity.getAccountId().replaceAll("\\s+","").length())) {
            msg += "Whitespaces are not allowed in customer ID!\n";
        }
        if (entity.getStake() <= 0) {
            msg += "Stake is invalid!\n";
        }

        if (!msg.equals("")) {
            throw new ValidatorException(msg);
        }
    }
}
