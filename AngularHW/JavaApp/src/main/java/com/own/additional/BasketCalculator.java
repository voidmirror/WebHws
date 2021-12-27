package com.own.additional;

import java.util.Arrays;

public class BasketCalculator {
    private BasketPosition[] basketPositions;
    private String username;
    private Double basketSum;

    public BasketCalculator() {
    }

    public BasketCalculator(BasketPosition[] basketPositions, String username, Double basketSum) {
        this.basketPositions = basketPositions;
        this.username = username;
        this.basketSum = basketSum;
    }

    public BasketPosition[] getBasketPositions() {
        return basketPositions;
    }

    public void setBasketPositions(BasketPosition[] basketPositions) {
        this.basketPositions = basketPositions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBasketSum() {
        return basketSum;
    }

    public void setBasketSum(Double basketSum) {
        this.basketSum = basketSum;
    }

    @Override
    public String toString() {
        return "BasketCalculator{" +
                "basketPositions=" + Arrays.toString(basketPositions) +
                ", username='" + username + '\'' +
                ", basketSum=" + basketSum +
                '}';
    }
}
