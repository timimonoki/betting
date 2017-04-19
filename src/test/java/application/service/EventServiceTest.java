package application.service;

import application.domain.Bet;
import application.domain.Customer;
import application.domain.Event;
import application.repository.EventRepository;
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
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService dummyService = new EventService();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dummyService = null;
    }

    @Test
    public void testDelete() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("Name");

        when(eventRepository.findOne(1)).thenReturn(dummyEvent);
        doNothing().when(eventRepository).delete(1);

        Event returnedEvent = dummyService.delete(1);

        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "Name");

        when(eventRepository.findOne(anyInt())).thenReturn(null);

        try {
            dummyService.delete(anyInt());
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "This ID doesn't exist!");
        }

    }

    @Test
    public void testFindAllEvents() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("Name");

        List<Event> toReturn = Arrays.asList(dummyEvent);

        when(eventRepository.findAll()).thenReturn(toReturn);

        List<Event> returnedList = dummyService.findAllEvents();
        assertEquals(returnedList.size(), 1);

        Event returnedEvent = returnedList.get(0);
        assertEquals(returnedEvent.getName(), "Name");

    }


    @Test
    public void testFindById() throws Exception {

        Event dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("Name");

        when(eventRepository.findOne(1)).thenReturn(dummyEvent);

        Event returnedEvent = dummyService.findById(1);
        assertEquals((int) returnedEvent.getId(), 1);
        assertEquals(returnedEvent.getName(), "Name");

        when(eventRepository.findOne(anyInt())).thenReturn(null);

        try {
            dummyService.findById(anyInt());
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "This ID dosen't exist!");
        }

    }

}