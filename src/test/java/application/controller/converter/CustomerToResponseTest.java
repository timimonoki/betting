package application.controller.converter;

import application.domain.Bet;
import application.domain.Customer;
import application.model.ResponseBet;
import application.model.ResponseCustomer;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerToResponseTest {

    @Mock
    private BetToResponse betConverter;

    @InjectMocks
    private CustomerToResponse converter = new CustomerToResponse(betConverter);

    private List<Customer> customerList;
    private Customer dummyCustomer1;
    private Customer dummyCustomer2;

    private List<Bet> betList;
    private Bet dummyBet1;
    private Bet dummyBet2;

    private List<ResponseBet> responseBetList;
    private ResponseBet dummyResponseBet1;
    private ResponseBet dummyResponseBet2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        setUpBets();
        setUpResponseBets();
        setUpCustomers();

    }

    private void setUpCustomers() throws Exception {
        dummyCustomer1 = new Customer();
        dummyCustomer1.setName("name");
        dummyCustomer1.setAccountId("accountId");
        dummyCustomer1.setBalance(100.0);
        dummyCustomer1.setBets(betList);

        dummyCustomer2 = new Customer();
        dummyCustomer2.setName("name");
        dummyCustomer2.setAccountId("accountId");
        dummyCustomer2.setBalance(100.0);
        dummyCustomer2.setBets(betList);

        customerList = Arrays.asList(dummyCustomer1, dummyCustomer2);
    }

    private void setUpBets() throws Exception {
        dummyBet1 = new Bet();
        dummyBet1.setBetcode(1L);

        dummyBet2 = new Bet();
        dummyBet2.setBetcode(2L);

        betList = Arrays.asList(dummyBet1, dummyBet2);
    }

    private void setUpResponseBets() throws Exception {

        dummyResponseBet1 = new ResponseBet();
        dummyResponseBet1.setEventName("name");

        dummyResponseBet2 = new ResponseBet();
        dummyResponseBet2.setEventName("name");

        responseBetList = Arrays.asList(dummyResponseBet1, dummyResponseBet2);

    }

    @After
    public void tearDown() throws Exception {
        betConverter = null;
        converter = null;
        dummyCustomer1 = null;
        dummyCustomer2 = null;
        betList = null;
        dummyBet1 = null;
        dummyBet2 = null;
        responseBetList = null;
        dummyResponseBet1 = null;
        dummyResponseBet2 = null;
    }

    @Test
    public void testConvert() throws Exception {

        when(betConverter.convert(betList)).thenReturn(responseBetList);

        ResponseCustomer responseCustomer = converter.convert(dummyCustomer1);

        assertEquals(responseCustomer.getName(), "name");

    }

    @Test
    public void testConvertList() throws Exception {

        when(betConverter.convert(betList)).thenReturn(responseBetList);

        List<ResponseCustomer> responseCustomerList = converter.convert(customerList);

        assertEquals(responseCustomerList.size(), 2);

    }

    @Test
    public void testConvertItems() throws Exception {

        when(betConverter.convert(betList)).thenReturn(responseBetList);

        List<ResponseCustomer> responseCustomerList = converter.convert(customerList);

        assertEquals(responseCustomerList.get(0).getAccountId(), dummyCustomer1.getAccountId());
        assertEquals(responseCustomerList.get(1).getAccountId(), dummyCustomer1.getAccountId());

    }

}