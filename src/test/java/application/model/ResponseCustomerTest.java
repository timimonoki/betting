package application.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ResponseCustomerTest {

    private ResponseCustomer dummyCustomer;
    private ResponseBet dummyBet1;
    private ResponseBet dummyBet2;
    private List<ResponseBet> dummyList;

    @Before
    public void setUp() throws Exception {

        dummyCustomer = new ResponseCustomer();
        dummyCustomer.setName("name");
        dummyCustomer.setAccountId("accountId");
        dummyCustomer.setBalance(10.0);

        dummyBet1 = new ResponseBet();
        dummyBet1.setEventName("name");
        dummyBet2 = new ResponseBet();
        dummyBet2.setEventName("name");

        dummyList = Arrays.asList(dummyBet1, dummyBet2);

        dummyCustomer.setBets(dummyList);
    }

    @After
    public void tearDown() throws Exception {
        dummyCustomer = null;
        dummyBet1 = null;
        dummyBet2 = null;
        dummyList = null;
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
        assertEquals(dummyCustomer.getBalance(), 10.0, 0.0);
    }

}