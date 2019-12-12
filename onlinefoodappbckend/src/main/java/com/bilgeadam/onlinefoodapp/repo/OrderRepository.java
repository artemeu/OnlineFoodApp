package com.bilgeadam.onlinefoodapp.repo;

import com.bilgeadam.onlinefoodapp.domain.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> getAllByCustomer_CustomerIdOrderByOrderIdDesc(Long id);
    Optional<Order> getByOrderId(Long id);

    @Query(value = "SELECT * FROM ORDERS WHERE ORDER_ID=(SELECT MAX(ORDER_ID) from ORDERS)", nativeQuery = true)
    Optional<Order> getLastOrderId();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ORDER_DETAILS " +
            "(ORDER_ID, CODE, UNIT_PRICE)" +
            " VALUES (:orderId, :code, :price)", nativeQuery = true)
    void createOrderDetail(long orderId, String code, long price);
}
