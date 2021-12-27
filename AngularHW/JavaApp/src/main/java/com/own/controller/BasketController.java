package com.own.controller;

import com.own.additional.BasketCalculator;
import com.own.additional.BasketCountObject;
import com.own.additional.BasketPosition;
import com.own.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/rest/v1/basket")
@CrossOrigin(origins = "http://localhost:4200")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping(value = "/calculate", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public double calculateBasketPrice(@RequestBody BasketPosition basketPosition) {
        System.out.println("INSIDE BASKET CONTROLLER");
        return basketService.calculatePrice(basketPosition);
    }

//    @PostMapping(value = "/calculate/all", consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public double calculateBasketPrice(@RequestBody BasketCountObject basketCountObject) {
//        System.out.println("INSIDE BASKET ALL PRICES CONTROLLER");
//        System.out.println(Arrays.toString(basketCountObject.getBasketPositions()));
//        if (basketCountObject.getBasketPositions().length == 0) {
//            return 0;
//        }
//        return basketService.calculateAllBasket(basketCountObject.getBasketPositions(), basketCountObject.getUsername());
//    }

//    @PostMapping(value = "/calculate/all", consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public double calculateBasketPrice(@RequestBody BasketCountObject basketCountObject) {
//        System.out.println("INSIDE BASKET ALL PRICES CONTROLLER");
//        System.out.println(Arrays.toString(basketCountObject.getBasketPositions()));
//        if (basketCountObject.getBasketPositions().length == 0) {
//            return 0;
//        }
//        return basketService.calculateAllBasket(basketCountObject.getBasketPositions(), basketCountObject.getUsername());
//    }

    @PostMapping(value = "/calculate/all", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BasketCalculator calculateBasketPrice(@RequestBody BasketCalculator basketCalculator) {
        System.out.println("INSIDE BASKET ALL PRICES CONTROLLER");
        System.out.println(basketCalculator);
        System.out.println(Arrays.toString(basketCalculator.getBasketPositions()));
        if (basketCalculator.getBasketPositions().length == 0) {
            basketCalculator.setBasketSum(0.0);
            return basketCalculator;
        }
        basketCalculator = basketService.reloadBasket(basketCalculator);
        basketCalculator.setBasketSum(basketService.calculateAllBasket(basketCalculator.getBasketPositions(), basketCalculator.getUsername()));
        System.out.println("END: " + basketCalculator);
        return basketCalculator;
    }

//    @PostMapping(value = "/calculate/all", consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public BasketPosition[] calculateBasketPrice(@RequestBody BasketCalculator basketCalculator) {
//        System.out.println("INSIDE BASKET ALL PRICES CONTROLLER");
//        System.out.println(basketCalculator);
//        System.out.println(Arrays.toString(basketCalculator.getBasketPositions()));
//        if (basketCalculator.getBasketPositions().length == 0) {
//            basketCalculator.setBasketSum(0.0);
//            return basketCalculator.getBasketPositions();
//        }
//        basketCalculator = basketService.reloadBasket(basketCalculator);
//        basketCalculator.setBasketSum(basketService.calculateAllBasket(basketCalculator.getBasketPositions(), basketCalculator.getUsername()));
//        System.out.println("END: " + basketCalculator);
//        return basketCalculator.getBasketPositions();
//    }

}
