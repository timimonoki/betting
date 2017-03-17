package application;

import domain.Bet;
import domain.Customer;
import domain.Event;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by NegrutiA on 3/14/2017.
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Bet.class);
        configuration.addAnnotatedClass(Event.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()). build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        SpringApplication.run(Application.class, args);
    }
}
