package com.own.controller;

import com.own.additional.DiscountChanger;
import com.own.additional.PersonalDiscountPromote;
import com.own.entity.Position;
import com.own.repository.DiscountRepository;
import com.own.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class DiscountController {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountService discountService;

    @PostMapping(value = "/discounts/ware", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addWareDiscount(@RequestBody DiscountChanger discountChanger) {
        System.out.println("Inside ware discount controller");
        discountService.changeWareDiscount(discountChanger);

    }

    @PostMapping(value = "/discounts/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUserDiscount(@RequestBody PersonalDiscountPromote discountChanger) {
        System.out.println("Inside user discount controller");
        discountService.changeUserDiscount(discountChanger);

    }

    @PostMapping(value = "/discounts/ware/delete", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addWareDiscount(@RequestBody Position position) {
        System.out.println("Inside delete discount controller");
        discountService.deleteWareDiscount(position);

    }

}
