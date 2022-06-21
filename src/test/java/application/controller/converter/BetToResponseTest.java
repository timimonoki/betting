package application.controller.converter;

import application.domain.Bet;
import application.domain.Customer;
import application.domain.Event;
import application.model.ResponseBet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BetToResponseTest {

    private BetToResponse converter;

    private Bet dummyBet1;
    private Bet dummyBet2;
    private List<Bet> betList;

    private Customer dummyCustomer;
    private Event dummyEvent;

    @Before
    public void setUp() throws Exception {

        converter = new BetToResponse();

        dummyCustomer = new Customer();
        dummyCustomer.setAccountId("accountId");
        dummyCustomer.setName("name");

        dummyEvent = new Event();
        dummyEvent.setName("name");

        dummyBet1 = new Bet();
        dummyBet1.setBetcode(1L);
        dummyBet1.setStake(1.0);
        dummyBet1.setCustomer(dummyCustomer);
        dummyBet1.setEvent(dummyEvent);

        dummyBet2 = new Bet();
        dummyBet2.setBetcode(1L);
        dummyBet2.setStake(1.0);
        dummyBet2.setCustomer(dummyCustomer);
        dummyBet2.setEvent(dummyEvent);

        betList = Arrays.asList(dummyBet1, dummyBet2);

    }

    @After
    public void tearDown() throws Exception {
        converter = null;
        dummyBet1 = null;
        dummyBet2 = null;
        dummyCustomer = null;
        dummyEvent = null;
        betList = null;
    }

    @Test
    public void testConvert() throws Exception {

        ResponseBet responseBet = converter.convert(dummyBet1);

        assertEquals(responseBet.getAccountId(), dummyCustomer.getAccountId());

    }

    @Test
    public void testConvertList() throws Exception {

        List<ResponseBet> responseBets = converter.convert(betList);

        assertEquals(responseBets.size(), 2);

    }

    @Test
    public void testConvertListItems() throws Exception {

        List<ResponseBet> responseBets = converter.convert(betList);

        assertEquals(responseBets.get(0).getAccountId(), dummyCustomer.getAccountId());
        assertEquals(responseBets.get(1).getAccountId(), dummyCustomer.getAccountId());
    }

}