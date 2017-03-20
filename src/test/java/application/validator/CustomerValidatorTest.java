package application.validator;

import application.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerValidatorTest {

    private CustomerValidator dummyValidator;

    @Before
    public void setUp() throws Exception {
        dummyValidator = new CustomerValidator();
    }

    @After
    public void tearDown() throws Exception {
        dummyValidator = null;
    }

    @Test
    public void testValidate() throws Exception {
        try {
            dummyValidator.validate(new Customer("", "", -1.0));
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Customer name is invalid!\n" +
                    "Customer ID is invalid!\n" +
                    "Customer balance shouldn't be negative!\n");
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }

        try {
            dummyValidator.validate(new Customer("A c\t s\t", "MyName", 5.0));
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Whitespaces are not allowed in customer ID!\n");
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }

        try {
            dummyValidator.validate(new Customer("accountId", "name", 5.0));
            assertEquals(true, true);
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }
    }

}