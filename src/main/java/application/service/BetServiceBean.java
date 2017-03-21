package application.service;

import application.domain.Bet;
import application.domain.Customer;
import application.repository.BetRepository;
import application.repository.CustomerRepository;
import application.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetServiceBean implements IService<Bet, Integer> {

    @Autowired
    private BetRepository betRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EventRepository eventRepository;

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
        List <Customer> resultCustomers =
                customerRepository
                .findAll()
                .stream()
                .filter(customer -> customer.getAccountId().equals(E.getAccountId()))
                .collect(Collectors.toList());

        if (eventRepository.findOne(E.getEventId()) == null) {
            throw new Exception("Event ID dosen't exist!");
        }
        else if (resultCustomers.size() != 1) {
            throw new Exception("Account ID is invalid!");
        }

        return betRepository.save(E);
    }

    @Override
    public List<Bet> findAll() {
        return betRepository.findAll();
    }

    @Override
    public Bet findById(Integer integer) throws Exception {
        Bet result = betRepository.findOne(integer);

        if (result == null) {
            throw new Exception("This ID dosen't exist!");
        }

        return result;
    }

    public Bet findByBetcode(Integer betcode) throws Exception {
        List<Bet> result = betRepository
                .findAll()
                .stream()
                .filter(bet -> bet.getBetcode().equals(betcode))
                .collect(Collectors.toList());

        if (result.size() == 0) {
            throw new Exception("This betcode dosen't exist!");
        }
        else if (result.size() > 1) {
            throw new Exception("Fatal Error!");
        }

        return result.get(0);
    }

    public List<Bet> findAllFromAccount(String accountId) {
        return betRepository
                .findAll()
                .stream()
                .filter(bet -> bet.getAccountId().equals(accountId))
                .collect(Collectors.toList());
    }
}
