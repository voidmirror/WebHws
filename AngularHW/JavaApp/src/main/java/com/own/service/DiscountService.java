package com.own.service;

import com.own.additional.DiscountChanger;
import com.own.additional.PersonalDiscountPromote;
import com.own.entity.Discount;
import com.own.entity.Position;
import com.own.entity.User;
import com.own.repository.DiscountRepository;
import com.own.repository.PositionRepository;
import com.own.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private UserRepository userRepository;

    public void changeWareDiscount(DiscountChanger discountChanger) {
        Position position = positionRepository.findById(discountChanger.getPosition().getId()).get();
        Discount discount = discountRepository.findByName(discountChanger.getDiscountName());

        position.addDiscount(discount);
        System.out.println("Changed discount " + position);
        positionRepository.save(position);
    }

    public void changeUserDiscount(PersonalDiscountPromote discountChanger) {
        User user = userRepository.findByUsername(discountChanger.getUsername()).get();
        Discount discount = discountRepository.findByName(discountChanger.getDiscountName());

        user.addDiscount(discount);
        System.out.println("Changed discount " + user);
        userRepository.save(user);
    }

    public void deleteWareDiscount(Position pos) {
        Position position = positionRepository.findById(pos.getId()).get();
        position.removeAllDiscounts();

        System.out.println("Changed discount " + position);
        positionRepository.save(position);
    }

}
