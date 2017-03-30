package application.service;

import application.domain.Bet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BetServiceBean implements IService<Bet, Integer> {

    final static Logger logger = LoggerFactory.getLogger(BetServiceBean.class);

    @Override
    public Bet update(Bet E) {
        return null;
    }

    @Override
    public Bet delete(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Bet create(Bet E) throws Exception {
        return E;
    }

    @Override
    public List<Bet> findAll() {
        return Arrays.asList(new Bet());
    }

    @Override
    public Bet findById(Integer integer) throws Exception {
        return new Bet();
    }

    public Bet findByBetcode(Long betcode) throws Exception {
        return new Bet();
    }

    public List<Bet> findAllFromAccount(String accountId) {
        return Arrays.asList(new Bet());
    }
}
