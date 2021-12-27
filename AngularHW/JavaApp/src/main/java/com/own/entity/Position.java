package com.own.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private String description;

    private String category;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Discount> discounts;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public void addDiscount(Discount discount) {
        if (discounts == null) {
            discounts = new HashSet<Discount>();
        }

        discounts.add(discount);
    }

    public void removeDiscount(Discount discount) {
        if (discounts.isEmpty()) {
            return;
        }

        discounts.remove(discount);
    }

    public void removeAllDiscounts() {
        discounts.clear();
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", discounts=" + (discounts == null ? "null" : Arrays.toString(discounts.toArray())) +
                '}';
    }

}
