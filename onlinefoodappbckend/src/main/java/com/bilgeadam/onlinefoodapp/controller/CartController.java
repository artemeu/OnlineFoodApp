package com.bilgeadam.onlinefoodapp.controller;

import com.bilgeadam.onlinefoodapp.domain.Meal;
import com.bilgeadam.onlinefoodapp.service.CustomerService;
import com.bilgeadam.onlinefoodapp.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/cart")
public class CartController {

    private final CustomerService customerService;
    private final MealService mealService;

    public CartController(CustomerService customerService, MealService mealService) {
        this.customerService = customerService;
        this.mealService = mealService;
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
        return mealService.getAllCart(id);
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
    public boolean submitOrder(HttpServletRequest req, @RequestBody List<Meal> meals) {
        long id = customerService.getId(req);

        List<Meal> mealList = meals;

        try {
            customerService.emptyCart(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
