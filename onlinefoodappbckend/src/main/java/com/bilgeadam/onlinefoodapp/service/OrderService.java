package com.bilgeadam.onlinefoodapp.service;

import com.bilgeadam.onlinefoodapp.domain.Customer;
import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    public Order save(Order order, Long id) {
        Optional<Customer> customer = customerService.findById(id);
        order.setCustomer(customer.get());
        return orderRepository.save(order);
    }

    public Optional<Order> getLastOrderId() {
        return orderRepository.getLastOrderId();
    }

    public void testOrderDetails(long orderId, String code, long price) {
        orderRepository.testOrderDetails(orderId, code, price);
    }

    public Optional<Order> getByOrderId(Long id) {
        return orderRepository.getByOrderId(id);
    }
}
