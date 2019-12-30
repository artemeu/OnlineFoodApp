package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.service.CustomerService;
import com.bilgeadam.onlinefoodapp.service.MealService;
import com.bilgeadam.onlinefoodapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final MealService mealService;

    public OrderController(OrderService orderService, CustomerService customerService, MealService mealService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.mealService = mealService;
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Order> getAllByCustomerId(HttpServletRequest req) {
        long id = customerService.getId(req);
        return orderService.getAllByCustomerId(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    public List<Meal> orderDetails(@PathVariable long orderId) {
        return mealService.getOrderDetals(orderId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/status/{orderId}")
    public boolean orderStatus(@PathVariable long orderId) {
        String resullt = orderService.getByOrderId(orderId);

        if (resullt == null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/approve/{orderId}")
    public void approveOrder(@PathVariable long orderId) {
        String resullt = orderService.getByOrderId(orderId);
        if (resullt != "true" || resullt != "false") {
            try {
                orderService.approveOrder(orderId, 1);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

}
