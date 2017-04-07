package application.service;

import application.dbmodel.Bet;
import application.dbmodel.Customer;
import application.dbmodel.Event;
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
import java.util.function.Predicate;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class BetServiceTest {

    @Mock
    private BetRepository betRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private BetService dummyService = new BetService();

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
        Bet dummyBet = new Bet();
        dummyBet.setId(1);
        dummyBet.setStake(1.0);

        assertEquals(dummyService.update(dummyBet), null);
    }

    @Test
    public void testDelete() throws Exception {
        assertEquals(dummyService.delete(1), null);
    }

    @Test
    public void testCreate() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setAccountId("accountId");
        dummyCustomer.setName("Name");
        dummyCustomer.setBalance(10.0);

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("EventName");

        Bet dummyBet = new Bet();
        dummyBet.setId(1);
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);

        List<Customer> toReturn = Arrays.asList(dummyCustomer);

        when(eventRepository.findOne(anyInt())).thenReturn(dummyEvent);
        when(customerRepository.findAll()).thenReturn(toReturn);
        when(betRepository.save(dummyBet)).thenReturn(dummyBet);
        dummyService.create(dummyBet);

        Bet returnedBet = dummyService.create(dummyBet);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

        dummyCustomer.setBalance(3.0);
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
        when(eventRepository.findOne(anyInt())).thenReturn(dummyEvent);
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

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("accountId");

        Bet dummyBet = new Bet();
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);

        List<Bet> toReturn = Arrays.asList(dummyBet);

        when(betRepository.findAll()).thenReturn(toReturn);

        List<Bet> returned = dummyService.findAll();

        assertEquals(returned.size(), 1);

        Bet returnedBet = returned.get(0);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

    }

    @Test
    public void testFilterBets() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("accountId");

        Bet dummyBet = new Bet();
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);

        Bet dummyBet2 = new Bet();
        dummyBet2.setEvent(dummyEvent);
        dummyBet2.setCustomer(dummyCustomer);
        dummyBet2.setStake(10.0);

        List<Bet> toReturn = Arrays.asList(dummyBet, dummyBet2);

        when(betRepository.findAll()).thenReturn(toReturn);

        Predicate<Bet> predicate = bet -> true;
        Predicate<Bet> predicate1 = bet -> bet.getStake() > 1.0;

        List<Bet> returned = dummyService.filterBets(1L, Arrays.asList(predicate));

        assertEquals(returned.size(), 1);

        Bet returnedBet = returned.get(0);

        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
        assertEquals(returnedBet.getStake(), 5.0, 0.0);

        returned = dummyService.filterBets(0L, Arrays.asList(predicate, predicate1));

        assertEquals(returned.size(), 2);

    }

    @Test
    public void testFindById() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("accountId");

        Bet dummyBet = new Bet();
        dummyBet.setId(1);
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);
        dummyBet.setBetcode(8999L);

        when(betRepository.findOne(1)).thenReturn(dummyBet);

        Bet returnedBet = dummyService.findById(1);

        assertEquals((int) returnedBet.getId(), 1);
        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
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

        Event dummyEvent = new Event();
        dummyEvent.setId(1);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("accountId");

        Bet dummyBet = new Bet();
        dummyBet.setId(1);
        dummyBet.setEvent(dummyEvent);
        dummyBet.setCustomer(dummyCustomer);
        dummyBet.setStake(5.0);
        dummyBet.setBetcode(8999L);

        List<Bet> toReturn = Arrays.asList(dummyBet);

        when(betRepository.findAll()).thenReturn(toReturn);

        Bet returnedBet = dummyService.findByBetcode(8999L);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "accountId");
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

        Event dummyEvent1 = new Event();
        dummyEvent1.setId(1);

        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setId(1);
        dummyCustomer1.setAccountId("myAccount");

        Event dummyEvent2 = new Event();
        dummyEvent2.setId(2);

        Customer dummyCustomer2 = new Customer();
        dummyCustomer2.setId(2);
        dummyCustomer2.setAccountId("notAccount");

        Bet dummyBet1 = new Bet();
        dummyBet1.setEvent(dummyEvent1);
        dummyBet1.setCustomer(dummyCustomer1);
        dummyBet1.setStake(11.0);

        Bet dummyBet2 = new Bet();
        dummyBet2.setEvent(dummyEvent2);
        dummyBet2.setCustomer(dummyCustomer2);
        dummyBet2.setStake(10.0);

        List<Bet> toReturn = Arrays.asList(dummyBet1, dummyBet2);

        when(betRepository.findAll()).thenReturn(toReturn);

        List<Bet> returned = dummyService.findAllFromAccount("myAccount");

        assertEquals(returned.size(), 1);

        Bet returnedBet = returned.get(0);

        assertEquals((int) returnedBet.getEvent().getId(), 1);
        assertEquals(returnedBet.getCustomer().getAccountId(), "myAccount");
        assertEquals(returnedBet.getStake(), 11.0, 0.0);
    }

}