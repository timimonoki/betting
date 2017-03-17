package application;

import application.domain.Bet;
import application.domain.Customer;
import application.domain.Event;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by NegrutiA on 3/14/2017.
 */

@SpringBootApplication
@EnableJpaRepositories("application.repository")
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
