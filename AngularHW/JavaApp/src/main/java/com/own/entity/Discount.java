package com.own.entity;

import javax.persistence.*;

@Entity(name = "discount")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discount_type",
        discriminatorType = DiscriminatorType.INTEGER)
//@DiscriminatorValue("0")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean stackable;

    public Discount() {}

    public Discount(String name) {
        this.name = name;
    }

    public Discount(int id, String name, boolean stackable) {
        this.id = id;
        this.name = name;
        this.stackable = stackable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public double applyDiscount(Double price, Integer num) {
        return price * 100;
    }
}
