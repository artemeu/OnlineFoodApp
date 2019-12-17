package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/courier")
public class CourierController {

    private final OrderService orderService;

    public CourierController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List getAll() {
        List couriers = orderService.findOrdersByStatus();
        return couriers;
    }

    @RequestMapping(path = "/{orderId}", method = RequestMethod.POST)
    public void deliver(@PathVariable long orderId) {
        orderService.deliver(orderId);
    }

}
