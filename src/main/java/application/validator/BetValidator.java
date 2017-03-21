package application.validator;

import application.domain.Bet;

public class BetValidator implements IValidator<Bet> {
    @Override
    public void validate(Bet entity) throws ValidatorException {
        String msg = "";

        if (entity.getAccountId().equals("")) {
            msg += "Account ID is invalid!\n";
        }
        if (entity.getStake() <= 0) {
            msg += "Stake is invalid!\n";
        }

        if (!msg.equals("")) {
            throw new ValidatorException(msg);
        }
    }
}
