package application.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseBetTest {

    private ResponseBet dummyBet;

    @Before
    public void setUp() throws Exception {
        dummyBet = new ResponseBet();
        dummyBet.setEventName("eventName");
        dummyBet.setBetcode(1L);
        dummyBet.setAccountName("accountName");
        dummyBet.setStake(5.0);
        dummyBet.setAccountId("accountId");
    }

    @After
    public void tearDown() throws Exception {
        dummyBet = null;
    }

    @Test
    public void testGetAccountId() throws Exception {
        assertEquals(dummyBet.getAccountId(), "accountId");
    }

    @Test
    public void testGetAccountName() throws Exception {
        assertEquals(dummyBet.getAccountName(), "accountName");
    }

    @Test
    public void testGetEventName() throws Exception {
        assertEquals(dummyBet.getEventName(), "eventName");
    }

    @Test
    public void testGetStake() throws Exception {
        assertEquals(dummyBet.getStake(), 5.0, 0.0);
    }

    @Test
    public void testGetBetcode() throws Exception {
        assertEquals((long) dummyBet.getBetcode(), 1L);
    }

}