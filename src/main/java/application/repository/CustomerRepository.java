package application.repository;

import application.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NegrutiA on 3/16/2017.
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {}