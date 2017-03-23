package application.controller;

import application.domain.Customer;
import application.service.CustomerServiceBean;
import application.validator.CustomerValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerValidator validator;

    @Mock
    private CustomerServiceBean customerService;

    @InjectMocks
    private CustomerController dummyController = new CustomerController();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dummyController = null;
    }


    @Test
    public void testGetCustomer() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("id");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(10.0);

        when(customerService.findById(1)).thenReturn(dummyCustomer);

        Customer returnedCustomer = dummyController.getCustomer(1);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "id");
        assertEquals(returnedCustomer.getName(), "name");
        assertEquals(returnedCustomer.getBalance(), 10.0, 0.0);

        try {
            dummyController.getCustomer(-1);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testAddCustomer() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("id");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(10.0);

        doNothing().when(validator).validate(dummyCustomer);
        when(customerService.create(dummyCustomer)).thenReturn(dummyCustomer);

        Customer returnedCustomer = dummyController.addCustomer(dummyCustomer);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "id");
        assertEquals(returnedCustomer.getName(), "name");
        assertEquals(returnedCustomer.getBalance(), 10.0, 0.0);

    }

    @Test
    public void testUpdateCustomer() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("id");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(10.0);

        Customer newCustomer = new Customer();
        newCustomer.setId(1);
        newCustomer.setAccountId("newid");
        newCustomer.setName("newname");
        newCustomer.setBalance(5.0);

        doNothing().when(validator).validate(dummyCustomer);
        when(customerService.findById(1)).thenReturn(dummyCustomer);
        when(customerService.update(newCustomer)).thenReturn(newCustomer);

        Customer returnedCustomer = dummyController.updateCustomer(newCustomer);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "newid");
        assertEquals(returnedCustomer.getName(), "newname");
        assertEquals(returnedCustomer.getBalance(), 5.0, 0.0);

        when(customerService.findById(anyInt())).thenReturn(null);
        try {
            dummyController.updateCustomer(newCustomer);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testRemoveCustomer() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("id");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(10.0);

        when(customerService.delete(1)).thenReturn(dummyCustomer);

        Customer returnedCustomer = dummyController.removeCustomer(1);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "id");
        assertEquals(returnedCustomer.getName(), "name");
        assertEquals(returnedCustomer.getBalance(), 10.0, 0.0);

        try {
            dummyController.removeCustomer(-1);
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "Invalid ID!\n");
        }

    }

    @Test
    public void testGetCustomers() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("id");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(10.0);

        List<Customer> toReturn = Arrays.asList(dummyCustomer);

        when(customerService.findAll()).thenReturn(toReturn);

        List<Customer> returnedList = dummyController.getCustomers();

        assertEquals(returnedList.size(), 1);

        Customer returnedCustomer = returnedList.get(0);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "id");
        assertEquals(returnedCustomer.getName(), "name");
        assertEquals(returnedCustomer.getBalance(), 10.0, 0.0);

    }

}