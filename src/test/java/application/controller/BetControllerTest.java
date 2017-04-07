package application.controller;

import application.controller.dto.BetDTO;
import application.domain.Bet;
import application.domain.Customer;
import application.domain.Event;
import application.service.BetService;
import application.service.CustomerService;
import application.service.EventService;
import application.validator.BetValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BetControllerTest {

    @Mock
    private BetService betService;

    @Mock
    private CustomerService customerService;

    @Mock
    private EventService eventService;

    @Mock
    private BetValidator validator;

    @InjectMocks
    private BetController dummyController = new BetController();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dummyController = null;
    }

    @Test
    public void testAddBet() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setAccountId("accountId");

        BetDTO dummyBetDTO = new BetDTO();
        dummyBetDTO.setAccountId("accountId");
        dummyBetDTO.setEventId(1);
        dummyBetDTO.setStake(5.0);

        Bet dummyBet = new Bet();
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);

        doNothing().when(validator).validate(dummyBetDTO);
        when(betService.create(any())).thenReturn(dummyBet);
        when(eventService.findById(dummyBetDTO.getEventId())).thenReturn(dummyEvent);
        when(customerService.findByAccountId(dummyBetDTO.getAccountId())).thenReturn(dummyCustomer);

        Bet returnedBet = dummyController.addBet(dummyBetDTO);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

    }

    @Test
    public void testGetBet() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setAccountId("accountId");

        Bet dummyBet = new Bet();
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);

        when(betService.findById(1)).thenReturn(dummyBet);

        Bet returnedBet = dummyController.getBet(1);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

        try {
            dummyController.getBet(-1);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testGetBets() throws Exception {

        Event dummyEvent1 = new Event();
        dummyEvent1.setId(1);
        Event dummyEvent2 = new Event();
        dummyEvent2.setId(2);

        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setAccountId("a");
        Customer dummyCustomer2 = new Customer();
        dummyCustomer2.setAccountId("b");

        Bet dummyBet1 = new Bet();
        dummyBet1.setEvent(dummyEvent1);
        dummyBet1.setCustomer(dummyCustomer1);
        dummyBet1.setStake(1.0);
        Bet dummyBet2 = new Bet();
        dummyBet2.setEvent(dummyEvent2);
        dummyBet2.setCustomer(dummyCustomer2);
        dummyBet2.setStake(2.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betService.findAll()).thenReturn(toReturn);

        List<Bet> returnedList = dummyController.getBets();

        assertEquals(returnedList.size(), 2);

        Bet returnedBet1 = returnedList.get(0);
        Bet returnedBet2 = returnedList.get(1);

        assertEquals((int) returnedBet1.getEvent().getId(), 1);
        assertEquals(returnedBet1.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

        assertEquals((int) returnedBet2.getEvent().getId(), 2);
        assertEquals(returnedBet2.getCustomer().getAccountId(), "b");
        assertEquals(returnedBet2.getStake(), 2.0, 0.0);

    }

    @Test
    public void testGetBetByBetcode() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setAccountId("a");

        Bet dummyBet = new Bet();
        dummyBet.setId(1);
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(1.0);
        dummyBet.setBetcode(99L);

        when(betService.findByBetcode(99L)).thenReturn(dummyBet);

        Bet returnedBet = dummyController.getBetByBetcode(99L);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet.getStake(), 1.0, 0.0);

        try {
            dummyController.getBetByBetcode(-10L);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid betcode!\n");
        }

    }

    @Test
    public void testGetAllFromAccount() throws Exception {

        Event dummyEvent1 = new Event();
        dummyEvent1.setId(1);
        Event dummyEvent2 = new Event();
        dummyEvent2.setId(2);

        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setAccountId("a");
        Customer dummyCustomer2 = new Customer();
        dummyCustomer2.setAccountId("a");

        Bet dummyBet1 = new Bet();
        dummyBet1.setEvent(dummyEvent1);
        dummyBet1.setCustomer(dummyCustomer1);
        dummyBet1.setStake(1.0);
        Bet dummyBet2 = new Bet();
        dummyBet2.setEvent(dummyEvent2);
        dummyBet2.setCustomer(dummyCustomer2);
        dummyBet2.setStake(2.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betService.findAllFromAccount("a")).thenReturn(toReturn);

        List<Bet> returnedList = dummyController.getAllFromAccount("a");

        assertEquals(returnedList.size(), 2);

        Bet returnedBet1 = returnedList.get(0);
        Bet returnedBet2 = returnedList.get(1);

        assertEquals((int) returnedBet1.getEvent().getId(), 1);
        assertEquals(returnedBet1.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

        assertEquals((int) returnedBet2.getEvent().getId(), 2);
        assertEquals(returnedBet2.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet2.getStake(), 2.0, 0.0);

    }

    @Test
    public void testFilterBets() throws Exception {

        Event dummyEvent1 = new Event();
        dummyEvent1.setId(1);
        Event dummyEvent2 = new Event();
        dummyEvent2.setId(2);

        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setAccountId("a");
        Customer dummyCustomer2 = new Customer();
        dummyCustomer2.setAccountId("a");

        Bet dummyBet1 = new Bet();
        dummyBet1.setEvent(dummyEvent1);
        dummyBet1.setCustomer(dummyCustomer1);
        dummyBet1.setStake(1.0);
        Bet dummyBet2 = new Bet();
        dummyBet2.setEvent(dummyEvent2);
        dummyBet2.setCustomer(dummyCustomer2);
        dummyBet2.setStake(2.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betService.filterBets(anyLong(), any())).thenReturn(toReturn);

        List<Bet> returnedList = dummyController.filterBets("a", 2.0, 5L, true);

        assertEquals(returnedList.size(), 2);

        Bet returnedBet1 = returnedList.get(0);
        Bet returnedBet2 = returnedList.get(1);

        assertEquals((int) returnedBet1.getEvent().getId(), 1);
        assertEquals(returnedBet1.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

        assertEquals((int) returnedBet2.getEvent().getId(), 2);
        assertEquals(returnedBet2.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet2.getStake(), 2.0, 0.0);

        dummyCustomer2.setAccountId("b");

        toReturn = Arrays.asList(dummyBet1);

        when(betService.filterBets(anyLong(), any())).thenReturn(toReturn);

        returnedList = dummyController.filterBets("", 2.0, 5L, false);

        assertEquals(returnedList.size(), 1);

        returnedBet1 = returnedList.get(0);

        assertEquals((int) returnedBet1.getEvent().getId(), 1);
        assertEquals(returnedBet1.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

        returnedList = dummyController.filterBets("", -2.0, 5L, false);

        assertEquals(returnedList.size(), 1);

        returnedBet1 = returnedList.get(0);

        assertEquals((int) returnedBet1.getEvent().getId(), 1);
        assertEquals(returnedBet1.getCustomer().getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

    }

}