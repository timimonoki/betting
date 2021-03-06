package application.service;

import application.domain.Bet;
import application.domain.Customer;
import application.repository.BetRepository;
import application.repository.CustomerRepository;
import application.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BetServiceBean implements IService<Bet, Integer> {

    final static Logger logger = LoggerFactory.getLogger(BetServiceBean.class);

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
                .filter(customer -> customer.getAccountId().equals(E.getCustomer().getAccountId()))
                .collect(Collectors.toList());

        if (eventRepository.findOne(E.getEvent().getId()) == null) {
            throw new Exception("Event ID dosen't exist!");
        }
        else if (resultCustomers.size() != 1) {
            throw new Exception("Account ID is invalid!");
        }
        else if (resultCustomers.get(0).getBalance() - E.getStake() < 0) {
            throw new Exception("There aren't sufficient money for this bet!");
        }

        Customer c = resultCustomers.get(0);

        logger.info("Customer balance: {}", c.getBalance());
        c.setBalance(c.getBalance() - E.getStake());
        logger.info("New Customer balance after a {} stake will be {}", E.getStake(), c.getBalance());

        customerRepository.save(c);

        UUID myuuid = UUID.randomUUID();

        Long myBetcode = myuuid.getLeastSignificantBits() & 0x7FFF_FFFF_FFFF_FFFFL;
        //Set the sign beat to 0 no matter what!

        E.setBetcode(myBetcode);

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

    public Bet findByBetcode(Long betcode) throws Exception {
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
                .filter(bet -> bet.getCustomer().getAccountId().equals(accountId))
                .collect(Collectors.toList());
    }
}
