package com.own.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String[] ingredList;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredList() {
        return ingredList;
    }

    public void setIngredList(String[] ingredList) {
        this.ingredList = ingredList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredList=" + Arrays.toString(ingredList) +
                ", description='" + description + '\'' +
                '}';
    }
}
