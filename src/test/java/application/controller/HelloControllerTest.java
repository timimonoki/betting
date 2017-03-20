package application.controller;

import application.controller.HelloController;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public class HelloControllerTest {
    @Test
    public void testIndex() throws Exception {
        HelloController hello = new HelloController();
        assertEquals(hello.index(), "Greetings from Spring Boot!");
    }

}