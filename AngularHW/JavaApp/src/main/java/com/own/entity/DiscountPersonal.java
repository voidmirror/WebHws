package com.own.entity;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Entity
@DiscriminatorValue("4")
public class DiscountPersonal extends Discount {

    public DiscountPersonal() {
        super("discountPersonal");
    }

    @Override
    public double applyDiscount(Double price, Integer num) {
        return price * 0.93;
    }
}
