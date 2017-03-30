package application.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.*;

public class ExceptionControllerTest {

    private ExceptionController dummyController;

    @Before
    public void setUp() throws Exception {
        dummyController = new ExceptionController();
    }

    @After
    public void tearDown() throws Exception {
        dummyController = null;
    }

    @Test
    public void testHandleError() throws Exception {
        Exception exc = new Exception("Some message");

        ModelAndView mav = dummyController.handleError(null, exc);
        assertEquals(mav.getViewName(), "error");
    }

}