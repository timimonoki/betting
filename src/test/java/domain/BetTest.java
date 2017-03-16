package domain;

import junit.framework.TestCase;
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
        dummyBet2 = new Bet(1, 1, "username", 5.0, 1234);
        dummyBet3 = new Bet(2, "bestuser", 100.0);
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
        assertEquals((int)dummyBet2.getBetcode(), 1234);
        assertEquals(dummyBet3.getBetcode(), null);
    }

    @Test
    public void testSetBetcode() throws Exception {
        dummyBet1.setBetcode(9999);
        dummyBet2.setBetcode(9999);
        dummyBet3.setBetcode(9999);

        assertEquals((int)dummyBet1.getBetcode(), 9999);
        assertEquals((int)dummyBet2.getBetcode(), 9999);
        assertEquals((int)dummyBet3.getBetcode(), 9999);
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
        assertEquals(dummyBet1.getEventId(), null);
        assertEquals((int)dummyBet2.getEventId(), 1);
        assertEquals((int)dummyBet3.getEventId(), 2);
    }

    @Test
    public void testSetEventId() throws Exception {
        dummyBet1.setEventId(1);
        dummyBet2.setEventId(1);
        dummyBet3.setEventId(1);

        assertEquals((int)dummyBet1.getEventId(), 1);
        assertEquals((int)dummyBet2.getEventId(), 1);
        assertEquals((int)dummyBet3.getEventId(), 1);
    }

    @Test
    public void testGetAccountId() throws Exception {
        assertEquals(dummyBet1.getAccountId(), null);
        assertEquals(dummyBet2.getAccountId(), "username");
        assertEquals(dummyBet3.getAccountId(), "bestuser");
    }

    @Test
    public void testSetAccountId() throws Exception {
        dummyBet1.setAccountId("user");
        dummyBet2.setAccountId("user");
        dummyBet3.setAccountId("user");

        assertEquals(dummyBet1.getAccountId(), "user");
        assertEquals(dummyBet2.getAccountId(), "user");
        assertEquals(dummyBet3.getAccountId(), "user");
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