package application.controller;

import application.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerControllerTest {

    private CustomerController dummyController;

    @Before
    public void setUp() throws Exception {
        dummyController = new CustomerController();
    }

    @After
    public void tearDown() throws Exception {
        dummyController = null;
    }

    @Test
    public void testAddNewCustomer() throws Exception {
        //dummyController.addNewCustomer(new Customer("accountId", "name", 5.0));
    }

    @Test
    public void testGetCustomer() throws Exception {

    }

}