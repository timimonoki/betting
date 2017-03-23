package application.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorExceptionTest {
    private ValidatorException dummyValidator;

    @Before
    public void setUp() throws Exception {
        dummyValidator = new ValidatorException("Message");
    }

    @After
    public void tearDown() throws Exception {
        dummyValidator = null;
    }

    @Test
    public void testMessage() {
        assertEquals(dummyValidator.getMessage(), "Message");
    }
}