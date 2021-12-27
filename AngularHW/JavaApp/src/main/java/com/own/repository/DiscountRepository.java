package com.own.repository;

import com.own.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Discount findByName(String name);
}
