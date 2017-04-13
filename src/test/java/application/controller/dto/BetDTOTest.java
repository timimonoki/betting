package application.controller.dto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BetDTOTest {

    private BetDTO dummyBet;

    @Before
    public void setUp() throws Exception {
        dummyBet = new BetDTO();
        dummyBet.setName("name");
        dummyBet.setAccountId("accountId");
        dummyBet.setStake(5.0);
    }

    @After
    public void tearDown() throws Exception {
        dummyBet = null;
    }

    @Test
    public void testGetStake() throws Exception {
        assertEquals(dummyBet.getStake(), 5.0, 0.0);
    }

    @Test
    public void testGetEventId() throws Exception {
        assertEquals(dummyBet.getName(), "name");
    }

    @Test
    public void testGetAccountId() throws Exception {
        assertEquals(dummyBet.getAccountId(), "accountId");
    }

}