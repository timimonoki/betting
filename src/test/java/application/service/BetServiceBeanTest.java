package application.service;

import application.domain.Bet;
import application.domain.Customer;
import application.domain.Event;
import application.repository.BetRepository;
import application.repository.CustomerRepository;
import application.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class BetServiceBeanTest {

    @Mock
    private BetRepository betRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private BetServiceBean dummyService = new BetServiceBean();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dummyService = null;
    }

    @Test
    public void testUpdate() throws Exception {
        assertEquals(dummyService.update(new Bet(1, "accountId", 1.0)), null);
    }

    @Test
    public void testDelete() throws Exception {
        assertEquals(dummyService.delete(1), null);
    }

    @Test
    public void testCreate() throws Exception {

        Customer dummyCustomer = new Customer("accountId", "Name", 10.0);
        Bet dummyBet = new Bet(1, "accountId", 5.0);

        List<Customer> toReturn = Arrays.asList(dummyCustomer);

        when(eventRepository.findOne(anyInt())).thenReturn(new Event(1, "EventName"));
        when(customerRepository.findAll()).thenReturn(toReturn);
        when(betRepository.save(dummyBet)).thenReturn(dummyBet);

        Bet returnedBet = dummyService.create(dummyBet);

        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

        dummyCustomer = new Customer("accountId", "name", 3.0);
        toReturn = Arrays.asList(dummyCustomer);

        when(customerRepository.findAll()).thenReturn(toReturn);

        try {
            dummyService.create(dummyBet);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "There aren't sufficient money for this bet!");
        }

        when(eventRepository.findOne(anyInt())).thenReturn(null);

        try {
            dummyService.create(dummyBet);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Event ID dosen't exist!");
        }

        toReturn = new ArrayList<>();
        when(eventRepository.findOne(anyInt())).thenReturn(new Event(1, "EventName"));
        when(customerRepository.findAll()).thenReturn(toReturn);

        try {
            dummyService.create(dummyBet);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Account ID is invalid!");
        }

    }

    @Test
    public void testFindAll() throws Exception {

        Bet dummyBet = new Bet(1, "accountId", 5.0);

        List<Bet> toReturn = Arrays.asList(dummyBet);

        when(betRepository.findAll()).thenReturn(toReturn);

        List<Bet> returned = dummyService.findAll();

        assertEquals(returned.size(), 1);

        Bet returnedBet = returned.get(0);

        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

    }

    @Test
    public void testFindById() throws Exception {

        Bet dummyBet = new Bet(1, 1, "accountId", 5.0, 8999L);

        when(betRepository.findOne(1)).thenReturn(dummyBet);

        Bet returnedBet = dummyService.findById(1);

        assertEquals((int) returnedBet.getId(), 1);
        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);
        assertEquals((long) returnedBet.getBetcode(), 8999L);

        when(betRepository.findOne(anyInt())).thenReturn(null);

        try {
            dummyService.findById(anyInt());
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "This ID dosen't exist!");
        }

    }

    @Test
    public void testFindByBetcode() throws Exception {

        Bet dummyBet = new Bet(1, 1, "accountId", 5.0, 8999L);

        List<Bet> toReturn = Arrays.asList(dummyBet);

        when(betRepository.findAll()).thenReturn(toReturn);

        Bet returnedBet = dummyService.findByBetcode(8999L);

        assertEquals((int) returnedBet.getId(), 1);
        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);
        assertEquals((long) returnedBet.getBetcode(), 8999L);

        toReturn = Arrays.asList(dummyBet, dummyBet);
        when(betRepository.findAll()).thenReturn(toReturn);

        try {
            dummyService.findByBetcode(8999L);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Fatal Error!");
        }

        toReturn = new ArrayList<>();
        when(betRepository.findAll()).thenReturn(toReturn);

        try {
            dummyService.findByBetcode(8999L);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "This betcode dosen't exist!");
        }

    }

    @Test
    public void testFindAllFromAccount() throws Exception {
        Bet dummyBet1 = new Bet(1, "myAccount", 11.0);
        Bet dummyBet2 = new Bet(2, "notAccount", 10.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betRepository.findAll()).thenReturn(toReturn);

        List<Bet> returned = dummyService.findAllFromAccount("myAccount");

        assertEquals(returned.size(), 1);

        Bet returnedBet = returned.get(0);

        assertEquals((int) returnedBet.getEventId(), 1);
        assertEquals(returnedBet.getAccountId(), "myAccount");
        assertEquals(returnedBet.getStake(), 11.0, 0.0);
    }

}