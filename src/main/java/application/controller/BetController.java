package application.controller;

import application.controller.converter.BetToResponse;
import application.controller.converter.IConverter;
import application.controller.dto.BetDTO;
import application.domain.Bet;
import application.model.ResponseBet;
import application.service.BetService;
import application.service.CustomerService;
import application.service.EventService;
import application.validator.BetValidator;
import application.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RestController
public class BetController {

    private BetService betService;
    private CustomerService customerService;
    private EventService eventService;

    private IValidator<BetDTO> validator;
    private IConverter<ResponseBet, Bet> converter;

    @Autowired
    public BetController(BetService betService, CustomerService customerService, EventService eventService, BetValidator validator, BetToResponse converter) {
        this.betService = betService;
        this.customerService = customerService;
        this.eventService = eventService;
        this.validator = validator;
        this.converter = converter;
    }

    private Bet convertDtoToBet(BetDTO betDTO) throws Exception {
        Bet bet = new Bet();
        bet.setEvent(eventService.findByName(betDTO.getName()));
        bet.setCustomer(customerService.findByAccountId(betDTO.getAccountId()));
        bet.setStake(betDTO.getStake());

        return bet;
    }

    @RequestMapping(value = "/addBet", method = RequestMethod.POST)
    public ResponseBet addBet(@RequestBody BetDTO betDTO) throws Exception {
        validator.validate(betDTO);

        Bet bet = convertDtoToBet(betDTO);
        Bet createdBet = betService.create(bet);

        return converter.convert(createdBet);
    }

    @RequestMapping(value = "/filterBets", method = RequestMethod.GET)
    public List<ResponseBet> filterBets(@RequestParam(value = "contains", defaultValue = "") String contains,
                                @RequestParam(value = "minStake", defaultValue = "-1") Double minStake,
                                @RequestParam(value = "limit", defaultValue = "0") Long limit,
                                @RequestParam(value = "above", defaultValue = "true") Boolean above) {

        List<Predicate<Bet>> predicateList = new ArrayList<>();

        if (!contains.equals("")) {
            predicateList.add(bet -> bet.getCustomer().getName().contains(contains));
        }
        if (minStake > 0 && above) {
            predicateList.add(bet -> bet.getStake() >= minStake);
        } else if (minStake > 0) {
            predicateList.add(bet -> bet.getStake() < minStake);
        }

        List<Bet> betList = betService.filterBets(limit, predicateList);

        return converter.convert(betList);
    }

    @RequestMapping(value = "/getBets", method = RequestMethod.GET)
    public List<ResponseBet> getBets() throws Exception {

        List<Bet> betList = betService.findAll();

        return converter.convert(betList);
    }

    @RequestMapping(value = "/getBet", method = RequestMethod.GET)
    public ResponseBet getBet(@RequestParam(value = "betcode", defaultValue = "-1") Long betcode) throws Exception {
        if (betcode < 0) {
            throw new Exception("Invalid betcode!\n");
        }
        Bet bet = betService.findByBetcode(betcode);

        return converter.convert(bet);
    }

    @RequestMapping(value = "/getBetsForAccount", method = RequestMethod.GET)
    public List<ResponseBet> getAllFromAccount(@RequestParam(value = "accountId", defaultValue = "") String accountId) throws Exception {
        customerService.findByAccountId(accountId);

        List<Bet> betList = betService.findAllFromAccount(accountId);

        return converter.convert(betList);
    }
}
