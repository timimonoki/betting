package application.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by NegrutiA on 3/15/2017.
 */
public class BetTest {

    private Bet dummyBet1;
    private Bet dummyBet2;
    private Bet dummyBet3;

    @Before
    public void setUp() throws Exception {
        dummyBet1 = new Bet();

        Event event1 = new Event();
        event1.setId(1);
        event1.setName("eventname");

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customer");
        customer1.setAccountId("accountId");
        customer1.setBalance(5.0);

        dummyBet2 = new Bet();
        dummyBet2.setId(1);
        dummyBet2.setEvent(event1);
        dummyBet2.setCustomer(customer1);
        dummyBet2.setStake(5.0);
        dummyBet2.setBetcode(1234L);

        Event event2 = new Event();
        event2.setId(2);
        event2.setName("eventname");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("customer");
        customer2.setAccountId("accountId");
        customer2.setBalance(5.0);

        dummyBet3 = new Bet();
        dummyBet3.setEvent(event2);
        dummyBet3.setCustomer(customer2);
        dummyBet3.setStake(100.0);

    }

    @After
    public void tearDown() throws Exception {
        dummyBet1 = null;
        dummyBet2 = null;
        dummyBet3 = null;
    }

    @Test
    public void testGetBetcode() throws Exception {
        assertEquals(dummyBet1.getBetcode(), null);
        assertEquals((long) dummyBet2.getBetcode(), 1234L);
        assertEquals(dummyBet3.getBetcode(), null);
    }

    @Test
    public void testSetBetcode() throws Exception {
        dummyBet1.setBetcode(9999L);
        dummyBet2.setBetcode(9999L);
        dummyBet3.setBetcode(9999L);

        assertEquals((long) dummyBet1.getBetcode(), 9999L);
        assertEquals((long) dummyBet2.getBetcode(), 9999L);
        assertEquals((long) dummyBet3.getBetcode(), 9999L);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(dummyBet1.getId(), null);
        assertEquals((int)dummyBet2.getId(), 1);
        assertEquals(dummyBet3.getId(), null);
    }

    @Test
    public void testSetId() throws Exception {
        dummyBet1.setId(2);
        dummyBet2.setId(2);
        dummyBet3.setId(2);

        assertEquals((int)dummyBet1.getId(), 2);
        assertEquals((int)dummyBet2.getId(), 2);
        assertEquals((int)dummyBet3.getId(), 2);
    }

    @Test
    public void testGetEventId() throws Exception {
        assertEquals(dummyBet1.getEvent(), null);
        assertEquals((int)dummyBet2.getEvent().getId(), 1);
        assertEquals((int)dummyBet3.getEvent().getId(), 2);
    }

    @Test
    public void testSetEventId() throws Exception {
        Event event1 = new Event();
        event1.setId(1);
        event1.setName("eventname");

        dummyBet1.setEvent(event1);
        dummyBet2.setEvent(event1);
        dummyBet3.setEvent(event1);

        assertEquals((int)dummyBet1.getEvent().getId(), 1);
        assertEquals((int)dummyBet2.getEvent().getId(), 1);
        assertEquals((int)dummyBet3.getEvent().getId(), 1);
    }

    @Test
    public void testGetAccountId() throws Exception {
        assertEquals(dummyBet1.getCustomer(), null);
        assertEquals(dummyBet2.getCustomer().getAccountId(), "accountId");
        assertEquals(dummyBet3.getCustomer().getAccountId(), "accountId");
    }

    @Test
    public void testSetAccountId() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customer");
        customer1.setAccountId("user");
        customer1.setBalance(5.0);

        dummyBet1.setCustomer(customer1);
        dummyBet2.setCustomer(customer1);
        dummyBet3.setCustomer(customer1);

        assertEquals(dummyBet1.getCustomer().getAccountId(), "user");
        assertEquals(dummyBet2.getCustomer().getAccountId(), "user");
        assertEquals(dummyBet3.getCustomer().getAccountId(), "user");
    }

    @Test
    public void testGetStake() throws Exception {
        assertEquals(dummyBet1.getStake(), null);
        assertEquals(dummyBet2.getStake(), 5.0, 0);
        assertEquals(dummyBet3.getStake(), 100.0, 0);
    }

    @Test
    public void testSetStake() throws Exception {
        dummyBet1.setStake(100.0);
        dummyBet2.setStake(100.0);
        dummyBet3.setStake(100.0);

        assertEquals(dummyBet1.getStake(), 100.0, 0);
        assertEquals(dummyBet2.getStake(), 100.0, 0);
        assertEquals(dummyBet3.getStake(), 100.0, 0);
    }

}