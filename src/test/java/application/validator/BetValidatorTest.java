package application.validator;

import application.domain.Bet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BetValidatorTest {

    private BetValidator dummyValidator;

    @Before
    public void setUp() throws Exception {
        dummyValidator = new BetValidator();
    }

    @After
    public void tearDown() throws Exception {
        dummyValidator = null;
    }

    @Test
    public void testValidate() throws Exception {
        try {
            dummyValidator.validate(new Bet(1, "", 0.0));
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Account ID is invalid!\n" +
                    "Stake is invalid!\n");
        }

        try {
            dummyValidator.validate(new Bet(1, "Name", 5.0));
            assertEquals(true, true);
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }
    }

}