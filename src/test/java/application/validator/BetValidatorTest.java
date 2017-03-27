package application.validator;

import application.controller.dto.BetDTO;
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

        BetDTO bet = new BetDTO();
        bet.setAccountId("");
        bet.setStake(-1.0);
        bet.setEventId(-1);

        try {
            dummyValidator.validate(bet);
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Event ID is invalid!\n" +
                    "Customer ID is invalid!\n" +
                    "Stake is invalid!\n");
        }

        bet.setAccountId("A c\t s\t");
        bet.setStake(5.0);
        bet.setEventId(5);

        try {
            dummyValidator.validate(bet);
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Whitespaces are not allowed in customer ID!\n");
        }

        bet.setAccountId("account");

        try {
            dummyValidator.validate(bet);
            assertEquals(true, true);
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }

        bet.setAccountId(null);
        try {
            dummyValidator.validate(bet);
            assertEquals(true, false);
        }
        catch (Exception exc) {
            assertEquals(exc.getMessage(), "Customer ID is invalid!\n");
        }
    }

}