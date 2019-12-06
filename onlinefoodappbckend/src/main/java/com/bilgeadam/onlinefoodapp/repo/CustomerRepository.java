package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "INSERT INTO CART (CUSTOMER_ID, CODE, IS_ACTIVE) VALUES ( :id, :code, 1)", nativeQuery = true)
    void addToCart(long id, String code);

    @Query(value = "SELECT * FROM CART WHERE CUSTOMER_ID = :id AND CODE = :code ", nativeQuery = true)
    List checkCart(long id, String code);

    @Query(value = "DELETE FROM CART WHERE CUSTOMER_ID=:id AND CODE =:code ", nativeQuery = true)
    void deleteCartMeal(long id, String code);

    @Query(value = "DELETE FROM CART WHERE CUSTOMER_ID =:id", nativeQuery = true)
    void emptyCart(long id);

}
