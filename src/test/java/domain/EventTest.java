package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public class EventTest {

    private Event dummyEvent1;
    private Event dummyEvent2;
    private Event dummyEvent3;
    private Bet dummyBet1;
    private Bet dummyBet2;

    @Before
    public void setUp() throws Exception {
        List<Bet> bets = new ArrayList<>();

        dummyBet1 = new Bet();
        dummyBet2 = new Bet(5, "account id", 50.0);
        bets.add(dummyBet1);
        bets.add(dummyBet2);

        dummyEvent1 = new Event();
        dummyEvent2 = new Event(1, "Event name");
        dummyEvent3 = new Event(2,"New Event", bets);
    }

    @After
    public void tearDown() throws Exception {
        dummyEvent1 = null;
        dummyEvent2 = null;
        dummyEvent3 = null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals(dummyEvent1.getId(), null);
        assertEquals((int)dummyEvent2.getId(), 1);
        assertEquals((int)dummyEvent3.getId(), 2);
    }

    @Test
    public void testSetId() throws Exception {
        dummyEvent1.setId(5);
        dummyEvent2.setId(5);
        dummyEvent3.setId(5);

        assertEquals((int)dummyEvent1.getId(), 5);
        assertEquals((int)dummyEvent2.getId(), 5);
        assertEquals((int)dummyEvent3.getId(), 5);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(dummyEvent1.getName(), null);
        assertEquals(dummyEvent2.getName(), "Event name");
        assertEquals(dummyEvent3.getName(), "New Event");
    }

    @Test
    public void testSetName() throws Exception {
        dummyEvent1.setName("Event");
        dummyEvent2.setName("Event");
        dummyEvent3.setName("Event");

        assertEquals(dummyEvent1.getName(), "Event");
        assertEquals(dummyEvent2.getName(), "Event");
        assertEquals(dummyEvent3.getName(), "Event");
    }

    @Test
    public void testGetBets() throws Exception {
        assertEquals(dummyEvent1.getBets(), null);
        assertTrue(dummyEvent2.getBets().isEmpty());
        List<Bet> bets = dummyEvent3.getBets();

        assertEquals(bets.size(), 2);
        assertEquals(bets.get(0), dummyBet1);
        assertEquals(bets.get(1), dummyBet2);
    }

    @Test
    public void testSetBets() throws Exception {
        List<Bet> bets = new ArrayList<>();
        Bet bet1 = new Bet();
        Bet bet2 = new Bet();
        Bet bet3 = new Bet();

        bets.add(bet1);
        dummyEvent1.setBets(bets);
        bets = dummyEvent1.getBets();
        assertEquals(bets.size(), 1);
        assertEquals(bets.get(0), bet1);

        bets.add(bet2);
        dummyEvent2.setBets(bets);
        bets = dummyEvent2.getBets();
        assertEquals(bets.size(), 2);
        assertEquals(bets.get(0), bet1);
        assertEquals(bets.get(1), bet2);

        bets.add(bet3);
        dummyEvent3.setBets(bets);
        bets = dummyEvent3.getBets();
        assertEquals(bets.size(), 3);
        assertEquals(bets.get(0), bet1);
        assertEquals(bets.get(1), bet2);
        assertEquals(bets.get(2), bet3);
    }

}