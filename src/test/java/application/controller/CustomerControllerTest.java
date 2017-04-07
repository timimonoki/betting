package application.controller;

import application.controller.dto.CustomerDTO;
import application.domain.Customer;
import application.service.CustomerService;
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
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerValidator validator;

    @Mock
    private CustomerService customerService;

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

        CustomerDTO dummyDTO = new CustomerDTO();
        dummyDTO.setAccountId("id");
        dummyDTO.setName("name");
        dummyDTO.setBalance(10.0);

        Customer dummyCustomer = new Customer();
        dummyCustomer.setAccountId("id");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(10.0);

        doNothing().when(validator).validate(dummyDTO);
        when(customerService.create(any())).thenReturn(dummyCustomer);

        Customer returnedCustomer = dummyController.addCustomer(dummyDTO);

        assertEquals(returnedCustomer.getAccountId(), "id");
        assertEquals(returnedCustomer.getName(), "name");
        assertEquals(returnedCustomer.getBalance(), 10.0, 0.0);
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        CustomerDTO dummyDTO = new CustomerDTO();
        dummyDTO.setId(1);
        dummyDTO.setAccountId("id");
        dummyDTO.setName("name");
        dummyDTO.setBalance(10.0);

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

        doNothing().when(validator).validate(dummyDTO);
        when(customerService.findById(dummyDTO.getId())).thenReturn(dummyCustomer);
        when(customerService.update(any())).thenReturn(newCustomer);

        Customer returnedCustomer = dummyController.updateCustomer(dummyDTO);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "newid");
        assertEquals(returnedCustomer.getName(), "newname");
        assertEquals(returnedCustomer.getBalance(), 5.0, 0.0);

        when(customerService.findById(dummyDTO.getId())).thenReturn(null);

        try {
            dummyController.updateCustomer(dummyDTO);
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