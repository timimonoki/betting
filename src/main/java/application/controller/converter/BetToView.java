package application.controller.converter;

import application.domain.Bet;
import application.model.ResponseBet;
import java.util.List;
import java.util.stream.Collectors;

public class BetToView implements IConverter<ResponseBet, Bet> {

    @Override
    public ResponseBet convert(Bet bet) {

        ResponseBet view = new ResponseBet();
        view.setBetcode(bet.getBetcode());
        view.setStake(bet.getStake());
        view.setAccountId(bet.getCustomer().getAccountId());
        view.setAccountName(bet.getCustomer().getName());
        view.setEventName(bet.getEvent().getName());

        return view;
    }

    public List<ResponseBet> convert(List<Bet> bets) {
        return bets.stream()
                .map(new BetToView()::convert)
                .collect(Collectors.toList());
    }

}
