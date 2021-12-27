package com.own.controller;

import com.own.entity.Position;
import com.own.entity.Recipe;
import com.own.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipe")
    public List<Recipe> retrieveAllPositions() {
        return recipeRepository.findAll();
    }


}
