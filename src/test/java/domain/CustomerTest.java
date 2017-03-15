package domain;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by NegrutiA on 3/15/2017.
 */
public class CustomerTest extends TestCase {

    private Customer dummyCustomer1;
    private Customer dummyCustomer2;

    @Override
    protected void setUp() throws Exception {
        dummyCustomer1 = new Customer();
        dummyCustomer2 = new Customer("username", "User Name", 700.0);
    }

    @Override
    protected void tearDown() throws Exception {
        dummyCustomer1 = null;
        dummyCustomer2 = null;

        assertNull(dummyCustomer1);
        assertNull(dummyCustomer2);
    }

    @Test
    public void testGetAccountId() throws Exception {
        assertEquals(dummyCustomer1.getAccountId(), null);
        assertEquals(dummyCustomer2.getAccountId(), "username");
    }

    @Test
    public void testSetAccountId() throws Exception {
        dummyCustomer1.setAccountId("user");
        dummyCustomer2.setAccountId("user");

        assertEquals(dummyCustomer1.getAccountId(), "user");
        assertEquals(dummyCustomer2.getAccountId(), "user");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(dummyCustomer1.getName(), null);
        assertEquals(dummyCustomer2.getName(), "User Name");
    }

    @Test
    public void testSetName() throws Exception {
        dummyCustomer1.setName("NAME");
        dummyCustomer2.setName("NAME");

        assertEquals(dummyCustomer1.getName(), "NAME");
        assertEquals(dummyCustomer2.getName(), "NAME");
    }

    @Test
    public void testGetBalance() throws Exception {
        assertEquals(dummyCustomer1.getBalance(), null);
        assertEquals(dummyCustomer2.getBalance(), 700.0);
    }

    @Test
    public void testSetBalance() throws Exception {
        dummyCustomer1.setBalance(100.0);
        dummyCustomer2.setBalance(100.0);

        assertEquals(dummyCustomer1.getBalance(), 100.0);
        assertEquals(dummyCustomer2.getBalance(), 100.0);
    }
}