package application.service;

import application.domain.Customer;
import application.repository.CustomerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceBeanTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceBean dummyService = new CustomerServiceBean();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        dummyService = null;
    }

    @Test
    public void testUpdate() throws Exception {

        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setId(1);
        dummyCustomer1.setAccountId("AccountId");
        dummyCustomer1.setName("name");
        dummyCustomer1.setBalance(100.0);

        Customer newCustomer = new Customer();
        newCustomer.setId(1);
        newCustomer.setAccountId("NewAccountId");
        newCustomer.setName("NewName");
        newCustomer.setBalance(99.0);

        when(customerRepository.getOne(newCustomer.getId())).thenReturn(dummyCustomer1);
        when(customerRepository.save(newCustomer)).thenReturn(null);

        Customer updatedCustomer = dummyService.update(newCustomer);

        assertEquals((int) updatedCustomer.getId(), 1);
        assertEquals(updatedCustomer.getAccountId(), "NewAccountId");
        assertEquals(updatedCustomer.getName(), "NewName");
        assertEquals(updatedCustomer.getBalance(), 99.0, 0.0);

    }

    @Test
    public void testDelete() throws Exception {

        Customer dummyCustomer1 = new Customer();
        dummyCustomer1.setId(1);
        dummyCustomer1.setAccountId("AccountId");
        dummyCustomer1.setName("name");
        dummyCustomer1.setBalance(100.0);

        when(customerRepository.findOne(anyInt())).thenReturn(null);

        try {
            dummyService.delete(anyInt());
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "This ID doesn't exist!");
        }

        when(customerRepository.findOne(1)).thenReturn(dummyCustomer1);
        doNothing().when(customerRepository).delete(1);

        Customer newCustomer = dummyService.delete(1);

        assertEquals((int) newCustomer.getId(), 1);
        assertEquals(newCustomer.getAccountId(), "AccountId");
        assertEquals(newCustomer.getName(), "name");
        assertEquals(newCustomer.getBalance(), 100.0, 0.0);

    }

    @Test
    public void testCreate() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("AccountId");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(100.0);

        List<Customer> toReturn = Arrays.asList(dummyCustomer);

        when(customerRepository.findAll()).thenReturn(toReturn);

        try {
            dummyService.create(dummyCustomer);
            assertEquals(true, false);
        }
        catch (Exception exc) {
            assertEquals(exc.getMessage(), "This customer ID already exists!");
        }

        toReturn = new ArrayList<>();

        when(customerRepository.findAll()).thenReturn(toReturn);
        when(customerRepository.save(dummyCustomer)).thenReturn(dummyCustomer);

        Customer returnedCustomer = dummyService.create(dummyCustomer);

        assertEquals((int) returnedCustomer.getId(), 1);
        assertEquals(returnedCustomer.getAccountId(), "AccountId");
        assertEquals(returnedCustomer.getName(), "name");
        assertEquals(returnedCustomer.getBalance(), 100.0, 0.0);

    }

    @Test
    public void testFindAll() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("AccountId");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(100.0);

        List<Customer> toReturn = Arrays.asList(dummyCustomer);

        when(customerRepository.findAll()).thenReturn(toReturn);

        List<Customer> newList = dummyService.findAll();
        assertEquals(newList.size(), 1);

        Customer customerInList = newList.get(0);

        assertEquals((int) customerInList.getId(), 1);
        assertEquals(customerInList.getAccountId(), "AccountId");
        assertEquals(customerInList.getName(), "name");
        assertEquals(customerInList.getBalance(), 100.0, 0.0);

    }

    @Test
    public void testFindById() throws Exception {

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId(1);
        dummyCustomer.setAccountId("AccountId");
        dummyCustomer.setName("name");
        dummyCustomer.setBalance(100.0);

        when(customerRepository.findOne(anyInt())).thenReturn(null);

        try {
            dummyService.findById(anyInt());
            assertEquals(true, false);
        } catch (Exception exc) {
            assertEquals(exc.getMessage(), "This ID doesn't exist!");
        }

        when(customerRepository.findOne(anyInt())).thenReturn(dummyCustomer);

        Customer newCustomer = dummyService.findById(1);

        assertEquals((int) newCustomer.getId(), 1);
        assertEquals(newCustomer.getAccountId(), "AccountId");
        assertEquals(newCustomer.getName(), "name");
        assertEquals(newCustomer.getBalance(), 100.0, 0.0);

    }

}