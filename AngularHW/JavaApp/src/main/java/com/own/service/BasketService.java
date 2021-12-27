package com.own.service;

import com.own.additional.BasketCalculator;
import com.own.additional.BasketPosition;
import com.own.entity.Discount;
import com.own.entity.DiscountPersonal;
import com.own.entity.Position;
import com.own.repository.PositionRepository;
import com.own.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private UserRepository userRepository;

    private final ArrayList<Discount> globalDiscounts = new ArrayList<>();

    public double calculatePrice(BasketPosition basketPosition) {
        System.out.println(basketPosition.getNum() * basketPosition.getPos().getPrice());
        return basketPosition.getNum() * basketPosition.getPos().getPrice();
    }

    public double calculateAllBasket(BasketPosition[] basketPositions, String username) {

        double sum = 0;

        for (BasketPosition basketPosition : basketPositions) {

            Position currentPosition = positionRepository.getById(basketPosition.getPos().getId());
            System.out.println("CURRENT POSITION:" + currentPosition);

                if (currentPosition.getDiscounts() != null && !currentPosition.getDiscounts().isEmpty()) {


                    ArrayList<Double> stackablePrice = new ArrayList<>();
                    ArrayList<Double> unstackablePrice = new ArrayList<>();
                    ArrayList<Discount> stackableDiscounts = new ArrayList<>();
                    ArrayList<Discount> unstackableDiscounts = new ArrayList<>();

                    for (Discount discount : currentPosition.getDiscounts()) {
                        if (discount.isStackable()) {
                            stackableDiscounts.add(discount);
                        } else {
                            unstackableDiscounts.add(discount);
                        }
                    }
                    for (Discount discount : stackableDiscounts) {
                        stackablePrice.add(discount.applyDiscount(currentPosition.getPrice(), basketPosition.getNum()));
                    }

                    double currentSum = 0;
                    if (!stackablePrice.isEmpty()) {
                        currentSum = Collections.min(stackablePrice);
                    } else {
                        currentSum = currentPosition.getPrice() * basketPosition.getNum();
                    }

                    for (Discount discount : unstackableDiscounts) {
                        currentSum = discount.applyDiscount(currentSum, basketPosition.getNum());
                    }

                    sum += currentSum;

                } else {
                    sum += currentPosition.getPrice() * basketPosition.getNum();
                }

        }

        sum = applyPersonalDiscounts(basketPositions, sum, username);

        sum = applyGlobalDiscounts(basketPositions, sum);

        return sum;
    }

    public double applyGlobalDiscounts(BasketPosition[] basketPositions, double sum) {
        int countWare = countWare(basketPositions);

        ArrayList<Double> stackablePrice = new ArrayList<>();
        ArrayList<Discount> unstackableDiscounts = new ArrayList<>();

        if (!globalDiscounts.isEmpty()) {

            for (Discount discount : globalDiscounts) {
                if (discount.isStackable()) {
                    stackablePrice.add(discount.applyDiscount(sum, countWare));
                } else {
                    unstackableDiscounts.add(discount);
                }
            }

            if (!stackablePrice.isEmpty()) {
                sum = Collections.min(stackablePrice);
            }

            if (!unstackableDiscounts.isEmpty()) {
                for (Discount d : unstackableDiscounts) {
                    sum = d.applyDiscount(sum, countWare);
                }
            }

        }

        return sum;
    }

    public double applyPersonalDiscounts(BasketPosition[] basketPositions, double sum, String username) {
        int countWare = countWare(basketPositions);

        ArrayList<Double> stackablePrice = new ArrayList<>();
        ArrayList<Discount> unstackableDiscounts = new ArrayList<>();

        Set<Discount> personalDiscounts = userRepository.findByUsername(username).get().getDiscounts();;

        if (personalDiscounts != null && !personalDiscounts.isEmpty()) {
            for (Discount discount : personalDiscounts) {
                if (discount.isStackable()) {
                    stackablePrice.add(discount.applyDiscount(sum, countWare));
                } else {
                    unstackableDiscounts.add(discount);
                }
            }

            if (!stackablePrice.isEmpty()) {
                sum = Collections.min(stackablePrice);
            }
            for (Discount discount : unstackableDiscounts) {
                sum = discount.applyDiscount(sum, countWare);
            }
        }

        return sum;



    }

    public BasketCalculator reloadBasket(BasketCalculator basketCalculator) {
        ArrayList<BasketPosition> newBasketPositions = new ArrayList<>();
        for (BasketPosition basketPosition : basketCalculator.getBasketPositions()) {
            Optional<Position> position = positionRepository.findById(basketPosition.getPos().getId());
            if (position.isPresent()) {
                basketPosition.setPos(position.get());
                newBasketPositions.add(basketPosition);
            }
        }
        basketCalculator.setBasketPositions(newBasketPositions.toArray(new BasketPosition[0]));
        return basketCalculator;
    }

    public int countWare(BasketPosition[] basketPositions) {
        int countWare = 0;
        for (BasketPosition countPosition : basketPositions) {
            countWare += countPosition.getNum();
        }
        System.out.println("Count Ware: " + countWare);
        return countWare;
    }

    public void addGlobalDiscount(Discount discount) {
        globalDiscounts.add(discount);
    }

}
