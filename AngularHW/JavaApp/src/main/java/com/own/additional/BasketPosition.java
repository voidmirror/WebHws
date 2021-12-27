package com.own.additional;

import com.own.entity.Position;
import org.springframework.stereotype.Component;

//@Component
public class BasketPosition {

    private int num;

    private Position pos;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "BasketPosition{" +
                "num=" + num +
                ", pos=" + pos +
                '}';
    }
}
