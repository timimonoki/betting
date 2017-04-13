package application.controller.dto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerDTOTest {

    private CustomerDTO dummyCustomer;

    @Before
    public void setUp() throws Exception {
        dummyCustomer = new CustomerDTO();
        dummyCustomer.setAccountId("accountId");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(5.0);
    }

    @After
    public void tearDown() throws Exception {
        dummyCustomer = null;
    }

    @Test
    public void testGetAccountId() throws Exception {
        assertEquals(dummyCustomer.getAccountId(), "accountId");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(dummyCustomer.getName(), "name");
    }

    @Test
    public void testGetBalance() throws Exception {
        assertEquals(dummyCustomer.getBalance(), 5.0, 0.0);
    }

}