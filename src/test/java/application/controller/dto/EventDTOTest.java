package application.controller.dto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventDTOTest {

    private EventDTO dummyEvent;

    @Before
    public void setUp() throws Exception {
        dummyEvent = new EventDTO();
        dummyEvent.setName("name");
        dummyEvent.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        dummyEvent = null;
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals((int) dummyEvent.getId(), 1);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(dummyEvent.getName(), "name");
    }

}