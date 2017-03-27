package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.Mockito.*;

/**
 * Created by NegrutiA on 3/16/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Test
    public void testMain() throws Exception {
        application.Application.main(new String[] {});
    }

    @Test
    public void testConfigure() throws Exception {
        Application app = new Application();
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder();

        app.configure(springApplicationBuilder);
    }

}