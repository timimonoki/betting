package application.controller;

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

        Event dummyEvent = new Event(1, "name");

        doNothing().when(validator).validate(dummyEvent);
        when(eventService.create(dummyEvent)).thenReturn(dummyEvent);

        Event returnedEvent = dummyController.addEvent(dummyEvent);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");

    }

    @Test
    public void testGetEvent() throws Exception {

        Event dummyEvent = new Event(1, "name");

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

        Event dummyEvent = new Event(1, "name");
        Event newEvent = new Event(1, "newname");

        doNothing().when(validator).validate(dummyEvent);
        when(eventService.findById(1)).thenReturn(dummyEvent);
        when(eventService.update(newEvent)).thenReturn(newEvent);

        Event returnedEvent = dummyController.updateEvent(newEvent);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "newname");

        when(eventService.findById(anyInt())).thenReturn(null);

        try {
            dummyController.updateEvent(newEvent);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testRemoveEvent() throws Exception {

        Event dummyEvent = new Event(1, "name");

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

        Event dummyEvent = new Event(1, "name");

        List<Event> toReturn = Arrays.asList(dummyEvent);

        when(eventService.findAll()).thenReturn(toReturn);

        List<Event> returnedList = dummyController.getEvents();

        assertEquals(returnedList.size(), 1);

        Event returnedEvent = returnedList.get(0);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "name");

    }

}