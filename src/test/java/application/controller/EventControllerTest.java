package application.controller;

import application.controller.dto.EventDTO;
import application.domain.Bet;
import application.domain.Event;
import application.service.EventServiceBean;
import application.validator.EventValidator;
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

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

    @Mock
    private EventServiceBean eventService;

    @Mock
    private EventValidator validator;

    @InjectMocks
    private EventController dummyController = new EventController();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dummyController = null;
    }

    @Test
    public void testAddEvent() throws Exception {

        EventDTO dummyEventDTO = new EventDTO();
        dummyEventDTO.setName("name");

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("name");

        doNothing().when(validator).validate(dummyEventDTO);
        when(eventService.create(any())).thenReturn(dummyEvent);

        Event returnedEvent = dummyController.addEvent(dummyEventDTO);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");

    }

    @Test
    public void testGetEvent() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("name");

        when(eventService.findById(1)).thenReturn(dummyEvent);

        Event returnedEvent = dummyController.getEvent(1);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");

        try {
            dummyController.getEvent(-1);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testUpdateEvent() throws Exception {

        EventDTO dummyEventDTO = new EventDTO();
        dummyEventDTO.setId(1);
        dummyEventDTO.setName("newname");

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("name");

        Event newEvent = new Event();
        newEvent.setId(1);
        newEvent.setName("newname");

        doNothing().when(validator).validate(dummyEventDTO);
        when(eventService.findById(dummyEventDTO.getId())).thenReturn(dummyEvent);
        when(eventService.update(any())).thenReturn(newEvent);

        Event returnedEvent = dummyController.updateEvent(dummyEventDTO);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "newname");

        when(eventService.findById(anyInt())).thenReturn(null);

        try {
            dummyController.updateEvent(dummyEventDTO);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testRemoveEvent() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("name");

        when(eventService.delete(1)).thenReturn(dummyEvent);

        Event returnedEvent = dummyController.removeEvent(1);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");

        try {
            dummyController.removeEvent(-1);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testGetEvents() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("name");

        Bet dummyBet1 = new Bet();
        dummyBet1.setBetcode(10L);

        Bet dummyBet2 = new Bet();
        dummyBet2.setBetcode(11L);

        List<Bet> betsList = Arrays.asList(dummyBet1, dummyBet2);
        dummyEvent.setBets(betsList);

        List<Event> toReturn = Arrays.asList(dummyEvent);

        when(eventService.findAll()).thenReturn(toReturn);

        List<Event> returnedList = dummyController.getEvents(true);

        assertEquals(returnedList.size(), 1);

        Event returnedEvent = returnedList.get(0);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");
        assertEquals(returnedEvent.getBets().size(), 2);

        dummyEvent.setBets(null);
        when(eventService.findAllEvents()).thenReturn(toReturn);

        returnedList = dummyController.getEvents(false);

        assertEquals(returnedList.size(), 1);

        returnedEvent = returnedList.get(0);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");
        assertEquals(returnedEvent.getBets(), null);

    }

}