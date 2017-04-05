package application.controller;

import application.controller.dto.BetDTO;
import application.domain.Bet;
import application.service.BetServiceBean;
import application.service.CustomerServiceBean;
import application.service.EventServiceBean;
import application.validator.BetValidator;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class BetController {

    @Autowired
    private BetServiceBean betService;

    @Autowired
    private CustomerServiceBean customerService;

    @Autowired
    private EventServiceBean eventService;

    private BetValidator validator;

    public BetController() { validator = new BetValidator(); }

    @RequestMapping(value = "/addBet", method = RequestMethod.POST)
    public Bet addBet(@RequestBody BetDTO betDTO) throws Exception {
        validator.validate(betDTO);

        Bet bet = new Bet();
        bet.setEvent(eventService.findById(betDTO.getEventId()));
        bet.setCustomer(customerService.findByAccountId(betDTO.getAccountId()));
        bet.setStake(betDTO.getStake());

        return betService.create(bet);
    }

    @RequestMapping(value = "/filterBets", method = RequestMethod.GET)
    public List<Bet> filterBets(@RequestParam(value = "contains", defaultValue = "") String contains,
                                @RequestParam(value = "minStake", defaultValue = "-1") Double minStake,
                                @RequestParam(value = "limit", defaultValue = "0") Long limit,
                                @RequestParam(value = "above", defaultValue = "true") Boolean above) {

        List<Predicate<Bet>> predicateList = new ArrayList<>();

        if (!contains.equals("")) {
            predicateList.add(bet -> bet.getCustomer().getName().contains(contains));
        }
        if (minStake > 0 && above) {
            predicateList.add(bet -> bet.getStake() > minStake);
        } else if (minStake > 0) {
            predicateList.add(bet -> bet.getStake() < minStake);
        }

        return betService.filterBets(limit, predicateList);
    }

    @RequestMapping(value = "/getBet", method = RequestMethod.GET)
    public Bet getBet(@RequestParam(value = "id", defaultValue = "-1") Integer id) throws Exception {
        if (id < 0) {
            throw new Exception("Invalid ID!\n");
        }
        return betService.findById(id);
    }

    @RequestMapping(value = "/getBets", method = RequestMethod.GET)
    public List<Bet> getBets() throws Exception {
        return betService.findAll();
    };

    @RequestMapping(value = "/getBetByBetcode", method = RequestMethod.GET)
    public Bet getBetByBetcode(@RequestParam(value = "betcode", defaultValue = "-1") Long betcode) throws Exception {
        if (betcode < 0) {
            throw new Exception("Invalid betcode!\n");
        }

        return betService.findByBetcode(betcode);
    }

    @RequestMapping(value = "/getBetsForAccount", method = RequestMethod.GET)
    public List<Bet> getAllFromAccount(@RequestParam(value = "accountId", defaultValue = "") String accountId) {
        return betService.findAllFromAccount(accountId);
    }
}
