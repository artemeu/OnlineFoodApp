package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.domain.Order;
import com.bilgeadam.onlinefoodapp.service.CustomerService;
import com.bilgeadam.onlinefoodapp.service.MealService;
import com.bilgeadam.onlinefoodapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cart")
public class CartController {

    private final CustomerService customerService;
    private final MealService mealService;
    private final OrderService orderService;

    public CartController(CustomerService customerService, MealService mealService, OrderService orderService) {
        this.customerService = customerService;
        this.mealService = mealService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{code}")
    public boolean addToCart(@PathVariable String code, HttpServletRequest req) {

        long id = customerService.getId(req);

        List checkCart = customerService.checkCart(id, code);
        if (checkCart.size() == 0) {
            try {
                customerService.addToCart(id, code);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List getCart(HttpServletRequest req) {
        long id = customerService.getId(req);
        List<Meal> meals = mealService.getAllCart(id);
        return meals;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{code}")
    public boolean delete(@PathVariable String code, HttpServletRequest req) {
        long id = customerService.getId(req);

        try {
            customerService.deleteCartMeal(id, code);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/submit")
    public void submitOrder(HttpServletRequest req, @RequestBody Set<Meal> meals) {
        long id = customerService.getId(req);
        long orderId = 1L;
        long price = 0L;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date();

        Optional<Order> lastOrder = orderService.getLastOrderId();

        if (lastOrder.isPresent()) {
            orderId = lastOrder.get().getOrderId();
            orderId += 1L;
        }

        for (Meal m : meals) {
            price += m.getPrice();
        }

        Order order = new Order();
        order.setOrderId(orderId);
        order.setPrice(price);
        order.setPlacementDate(date);
        order.setStatus(null);

        try {
            orderService.save(order, id);
            for (Meal m : meals) {
                orderService.createOrderDetail(orderId, m.getCode(), m.getPrice());
            }
            customerService.emptyCart(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
