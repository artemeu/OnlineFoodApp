package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByCustomerId(Long id);

    @Query(value = "INSERT INTO CART (CUSTOMER_ID, CODE, IS_ACTIVE) VALUES ( :id, :code, 1)", nativeQuery = true)
    void addToCart(long id, String code);

    @Query(value = "SELECT * FROM CART WHERE CUSTOMER_ID = :id AND CODE = :code ", nativeQuery = true)
    List checkCart(long id, String code);

    @Query(value = "DELETE FROM CART WHERE CUSTOMER_ID=:id AND CODE =:code ", nativeQuery = true)
    void deleteCartMeal(long id, String code);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CART WHERE CUSTOMER_ID =:id", nativeQuery = true)
    void emptyCart(long id);

}
