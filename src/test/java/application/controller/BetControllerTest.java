package application.controller;

import application.domain.Bet;
import application.service.BetServiceBean;
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
    private BetServiceBean betService;

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

        Bet dummyBet = new Bet(1, "accountId", 5.0);

        doNothing().when(validator).validate(dummyBet);
        when(betService.create(dummyBet)).thenReturn(dummyBet);

        Bet returnedBet = dummyController.addBet(dummyBet);

        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

    }

    @Test
    public void testGetBet() throws Exception {

        Bet dummyBet = new Bet(1, "accountId", 5.0);

        when(betService.findById(1)).thenReturn(dummyBet);

        Bet returnedBet = dummyController.getBet(1);

        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "accountId");
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

        Bet dummyBet1 = new Bet(1, "a", 1.0);
        Bet dummyBet2 = new Bet(2, "b", 2.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betService.findAll()).thenReturn(toReturn);

        List<Bet> returnedList = dummyController.getBets();

        assertEquals(returnedList.size(), 2);

        Bet returnedBet1 = returnedList.get(0);
        Bet returnedBet2 = returnedList.get(1);

        assertEquals((int) returnedBet1.getEventId(), 1);
        assertEquals(returnedBet1.getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

        assertEquals((int) returnedBet2.getEventId(), 2);
        assertEquals(returnedBet2.getAccountId(), "b");
        assertEquals(returnedBet2.getStake(), 2.0, 0.0);

    }

    @Test
    public void testGetBetByBetcode() throws Exception {

        Bet dummyBet = new Bet(1, 1, "a", 1.0, 99L);

        when(betService.findByBetcode(99L)).thenReturn(dummyBet);

        Bet returnedBet = dummyController.getBetByBetcode(99L);

        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "a");
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

        Bet dummyBet1 = new Bet(1, "a", 1.0);
        Bet dummyBet2 = new Bet(2, "a", 2.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betService.findAllFromAccount("a")).thenReturn(toReturn);

        List<Bet> returnedList = dummyController.getAllFromAccount("a");

        assertEquals(returnedList.size(), 2);

        Bet returnedBet1 = returnedList.get(0);
        Bet returnedBet2 = returnedList.get(1);

        assertEquals((int) returnedBet1.getEventId(), 1);
        assertEquals(returnedBet1.getAccountId(), "a");
        assertEquals(returnedBet1.getStake(), 1.0, 0.0);

        assertEquals((int) returnedBet2.getEventId(), 2);
        assertEquals(returnedBet2.getAccountId(), "a");
        assertEquals(returnedBet2.getStake(), 2.0, 0.0);

    }

}