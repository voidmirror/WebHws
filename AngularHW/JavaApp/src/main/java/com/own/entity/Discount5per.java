package com.own.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Component
@Entity
@DiscriminatorValue("1")
public class Discount5per extends Discount {

    public Discount5per() {
        super("discount5per");
    }

    @Override
    public double applyDiscount(Double price, Integer num) {
        return price * num * 0.95;
    }
}
