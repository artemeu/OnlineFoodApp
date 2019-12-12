package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.service.CustomerService;
import com.bilgeadam.onlinefoodapp.service.MealService;
import com.bilgeadam.onlinefoodapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService  orderService;
    private final CustomerService customerService;
    private final MealService mealService;

    public OrderController(OrderService orderService, CustomerService customerService, MealService mealService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.mealService = mealService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Order> getAllByCustomerId(HttpServletRequest req){
        long id = customerService.getId(req);
        return orderService.getAllByCustomerId(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{orderId}")
    public List<Meal> orderDetails(@PathVariable long orderId) { ;
       return mealService.getOrderDetals(orderId);
    }


}
