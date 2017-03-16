package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public class DBConnectionTest {

    private DBConnection dummyConnection1;
    private DBConnection dummyConnection2;

    @After
    public void tearDown() throws Exception {
        dummyConnection1 = null;
        dummyConnection2 = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        dummyConnection1 = DBConnection.getInstance();
        dummyConnection2 = DBConnection.getInstance();

        assertEquals(dummyConnection1, dummyConnection2);
    }

}