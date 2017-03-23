package application.controller;

import application.domain.Bet;
import application.domain.Customer;
import application.service.BetServiceBean;
import application.validator.BetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BetController {

    @Autowired
    private BetServiceBean betService;
    private BetValidator validator;

    public BetController() { validator = new BetValidator(); }

    @RequestMapping(value = "/addBet", method = RequestMethod.POST)
    public Bet addBet(@RequestBody Bet bet) throws Exception {
        validator.validate(bet);
        return betService.create(bet);
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
