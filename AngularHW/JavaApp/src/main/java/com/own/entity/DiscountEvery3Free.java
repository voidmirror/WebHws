package com.own.entity;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Entity
@DiscriminatorValue("3")
public class DiscountEvery3Free extends Discount {
    public DiscountEvery3Free() {
        super("discountEvery3Free");
    }

    @Override
    public double applyDiscount(Double price, Integer num) {
        double individualPrice = price / num;
        return Math.floorDiv(num, 3) > 0 ? price - (individualPrice * (Math.floorDiv(num, 3))) : price;
    }
}
