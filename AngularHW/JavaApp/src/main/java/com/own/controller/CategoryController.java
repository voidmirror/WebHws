package com.own.controller;

import com.own.additional.DiscountChanger;
import com.own.additional.PersonalDiscountPromote;
import com.own.entity.Category;
import com.own.entity.Position;
import com.own.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> retrieveAllCategories() {
//        System.out.println(categoryRepository.findAll());
        return categoryRepository.findAll();
    }

    @PostMapping(value = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addUserDiscount(@RequestBody Category category) {
        System.out.println(category.getId());
        System.out.println(category.getName());
        categoryRepository.save(category);

    }

}
