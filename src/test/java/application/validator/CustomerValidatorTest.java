package application.validator;

import application.controller.dto.CustomerDTO;
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

        CustomerDTO customer = new CustomerDTO();
        customer.setName("");
        customer.setAccountId("");
        customer.setBalance(-1.0);

        try {
            dummyValidator.validate(customer);
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

        customer.setName("name");
        customer.setBalance(5.0);
        customer.setAccountId("A c\t s\t");

        try {
            dummyValidator.validate(customer);
            assertEquals(true, false);
        }
        catch (ValidatorException exc) {
            assertEquals(exc.getMessage(), "Whitespaces are not allowed in customer ID!\n");
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }

        customer.setAccountId("account");

        try {
            dummyValidator.validate(customer);
            assertEquals(true, true);
        }
        catch (Exception exc) {
            assertEquals(true, false);
        }
    }

}