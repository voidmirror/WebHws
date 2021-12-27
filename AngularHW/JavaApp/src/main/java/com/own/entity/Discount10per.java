package com.own.entity;

import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Entity
@DiscriminatorValue("2")
public class Discount10per extends Discount {

    public Discount10per() {
        super("discount10per");
    }

    @Override
    public double applyDiscount(Double price, Integer num) {
        return price * num * 0.90;
    }
}
