package com.own.additional;

public class BasketCountObject {
    private BasketPosition[] basketPositions;

    private String username;

    public BasketCountObject(BasketPosition[] basketPositions, String username) {
        this.basketPositions = basketPositions;
        this.username = username;
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
}
