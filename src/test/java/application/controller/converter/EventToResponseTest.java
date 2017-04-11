package application.controller.converter;

import application.domain.Bet;
import application.domain.Event;
import application.model.ResponseBet;
import application.model.ResponseEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EventToResponseTest {

    @Mock
    private BetToResponse betConverter;

    @InjectMocks
    private EventToResponse converter = new EventToResponse(betConverter);

    private List<Event> eventList;
    private Event dummyEvent1;
    private Event dummyEvent2;

    private List<Bet> betList;
    private Bet dummyBet1;
    private Bet dummyBet2;

    private List<ResponseBet> responseBetList;
    private ResponseBet dummyResponseBet1;
    private ResponseBet dummyResponseBet2;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        setUpBets();
        setUpResponseBets();
        setUpEvents();

    }

    private void setUpBets() throws Exception {
        dummyBet1 = new Bet();
        dummyBet1.setBetcode(1L);

        dummyBet2 = new Bet();
        dummyBet2.setBetcode(2L);

        betList = Arrays.asList(dummyBet1, dummyBet2);
    }

    private void setUpResponseBets() throws Exception {

        dummyResponseBet1 = new ResponseBet();
        dummyResponseBet1.setEventName("name");

        dummyResponseBet2 = new ResponseBet();
        dummyResponseBet2.setEventName("name");

        responseBetList = Arrays.asList(dummyResponseBet1, dummyResponseBet2);

    }

    private void setUpEvents() throws Exception {
        dummyEvent1 = new Event();
        dummyEvent1.setName("name");
        dummyEvent1.setBets(betList);

        dummyEvent2 = new Event();
        dummyEvent2.setName("name");
        dummyEvent2.setBets(betList);

        eventList = Arrays.asList(dummyEvent1, dummyEvent2);
    }

    @After
    public void tearDown() throws Exception {
        converter = null;
        dummyEvent1 = null;
        dummyEvent2 = null;
        betList = null;
        dummyBet1 = null;
        dummyBet2 = null;
        dummyResponseBet1 = null;
        dummyResponseBet2 = null;
        eventList = null;
    }

    @Test
    public void testConvert() throws Exception {
        when(betConverter.convert(betList)).thenReturn(responseBetList);

        ResponseEvent responseEvent = converter.convert(dummyEvent1);

        assertEquals(responseEvent.getName(), "name");
    }

    @Test
    public void testConvertListSize() throws Exception {
        when(betConverter.convert(betList)).thenReturn(responseBetList);

        List<ResponseEvent> responseEventList = converter.convert(eventList);

        assertEquals(responseEventList.size(), 2);
    }

    @Test
    public void testConvertListItems() throws Exception {
        when(betConverter.convert(betList)).thenReturn(responseBetList);

        List<ResponseEvent> responseEventList = converter.convert(eventList);

        assertEquals(responseEventList.get(0).getName(), "name");
        assertEquals(responseEventList.get(1).getName(), "name");
    }

}