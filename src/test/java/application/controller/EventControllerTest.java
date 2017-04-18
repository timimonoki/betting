package application.controller;

import application.controller.converter.EventToResponse;
import application.controller.dto.EventDTO;
import application.domain.Event;
import application.model.ResponseEvent;
import application.service.EventService;
import application.validator.EventValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

    @Mock
    private EventService dummyEventService;

    @Mock
    private EventValidator validator;

    @Mock
    private EventToResponse converter;

    @InjectMocks
    private EventController dummyController = new EventController(dummyEventService, validator, converter);

    private EventDTO dummyDTO;

    private Event dummyEvent;

    private ResponseEvent dummyResponseEvent;

    @Before
    public void setUp() throws Exception {

        dummyDTO = new EventDTO();
        dummyDTO.setName("name");

        dummyEvent = new Event();
        dummyEvent.setId(1);
        dummyEvent.setName("name");

        dummyResponseEvent = new ResponseEvent();
        dummyResponseEvent.setName("name");

    }

    @After
    public void tearDown() throws Exception {
        dummyController = null;
        dummyDTO = null;
        dummyEvent = null;
        dummyResponseEvent = null;
    }

    @Test
    public void testAddEvent() throws Exception {

        doNothing().when(validator).validate(dummyDTO);
        when(dummyEventService.create(any())).thenReturn(dummyEvent);
        when(converter.convert(dummyEvent)).thenReturn(dummyResponseEvent);

        assertEquals(dummyController.addEvent(dummyDTO), dummyResponseEvent);

    }

    @Test
    public void testGetEventValidId() throws Exception {


    }

    @Test
    public void testGetEventInvalidId() throws Exception {

    }

    @Test
    public void testUpdateEvent() throws Exception {

    }

    @Test
    public void testRemoveEvent() throws Exception {

    }

    @Test
    public void testGetEvents() throws Exception {

    }

    @Test
    public void testGetEventsWithMostBets() throws Exception {

    }

    @Test
    public void testGetUniqueCustomersOnEventBets() throws Exception {

    }

}