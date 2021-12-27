package com.own.additional;

import com.own.entity.Position;

public class DiscountChanger {

    private Position position;
    private String discountName;

    public DiscountChanger(Position position, String discountName) {
        this.position = position;
        this.discountName = discountName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }
}
