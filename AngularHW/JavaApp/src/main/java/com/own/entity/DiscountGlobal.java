package com.own.entity;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Entity
@DiscriminatorValue("5")
public class DiscountGlobal extends Discount {
    public DiscountGlobal() {
        super("discountGlobal");
    }

    @Override
    public double applyDiscount(Double price, Integer num) {
        return price * 0.99;
    }
}
