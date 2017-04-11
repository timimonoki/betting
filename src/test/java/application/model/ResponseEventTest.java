package application.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ResponseEventTest {

    private ResponseEvent dummyEvent;
    private ResponseBet dummyBet1;
    private ResponseBet dummyBet2;
    private List<ResponseBet> dummyList;

    @Before
    public void setUp() throws Exception {
        dummyEvent = new ResponseEvent();
        dummyEvent.setName("name");

        dummyBet1 = new ResponseBet();
        dummyBet1.setEventName("name");
        dummyBet2 = new ResponseBet();
        dummyBet2.setEventName("name");

        dummyList = Arrays.asList(dummyBet1, dummyBet2);

        dummyEvent.setBets(dummyList);
    }

    @After
    public void tearDown() throws Exception {
        dummyEvent = null;
        dummyBet1 = null;
        dummyBet2 = null;
        dummyList = null;
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(dummyEvent.getName(), "name");
    }

    @Test
    public void testGetBets() throws Exception {
        assertEquals(dummyEvent.getBets(), dummyList);
    }

}