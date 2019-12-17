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

    @Query(value = "SELECT O.STATUS FROM ORDERS O WHERE O.ORDER_ID =:id", nativeQuery = true)
    String getByOrderId(Long id);

    @Query(value = "SELECT * FROM ORDERS ORDER BY PLACEMENT_DATE DESC", nativeQuery = true)
    List<Order> findAll();

    @Query(value = "SELECT O.ORDER_ID, C.NAME, C.SURNAME, O.PRICE, C.ADDRESS " +
            "FROM CUSTOMER C " +
            "INNER JOIN ORDERS O ON C.CUSTOMER_ID = O.CUSTOMER_ID " +
            "WHERE O.STATUS = 1", nativeQuery = true)
    List findOrdersByStatus();

    @Query(value = "SELECT * FROM ORDERS WHERE ORDER_ID=(SELECT MAX(ORDER_ID) from ORDERS)", nativeQuery = true)
    Optional<Order> getLastOrderId();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ORDER_DETAILS " +
            "(ORDER_ID, CODE, UNIT_PRICE)" +
            " VALUES (:orderId, :code, :price)", nativeQuery = true)
    void createOrderDetail(long orderId, String code, long price);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ORDERS SET STATUS =:status WHERE ORDER_ID =:orderId", nativeQuery = true)
    void approveOrder(long orderId, long status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ORDERS SET STATUS = 0 WHERE ORDER_ID =:orderId", nativeQuery = true)
    void deliver(long orderId);
}
