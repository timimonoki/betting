package application.controller.converter;

import application.domain.Bet;
import application.model.BetView;

public class BetToView implements IConverter<BetView, Bet> {

    @Override
    public BetView convert(Bet bet) {

        BetView view = new BetView();
        view.setBetcode(bet.getBetcode());
        view.setStake(bet.getStake());

        return null;
    }

}
