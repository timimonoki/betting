package repository;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by NegrutiA on 3/16/2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
