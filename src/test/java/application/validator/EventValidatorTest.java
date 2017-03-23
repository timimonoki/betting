package application.validator;

import application.controller.dto.EventDTO;
import application.domain.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventValidatorTest {

    private EventValidator dummyValidator;

    @Before
    public void setUp() throws Exception {
        dummyValidator = new EventValidator();
    }

    @After
    public void tearDown() throws Exception {
        dummyValidator = null;
    }

    @Test
    public void testValidate() throws Exception {

        EventDTO event = new EventDTO();
        event.setName("");

        try {
            dummyValidator.validate(event);
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Event name is invalid!\n");
        }

        event.setName("Name");
        try {
            dummyValidator.validate(event);
            assertEquals(true, true);
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }
    }

}