package com.own.additional;

public class PersonalDiscountPromote {

    private String username;
    private String discountName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public PersonalDiscountPromote(String username, String discountName) {
        this.username = username;
        this.discountName = discountName;
    }
}
