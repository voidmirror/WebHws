package com.own.entity;

import com.own.repository.RoleRepository;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "user", indexes = @Index(name = "user_username_index", columnList = "username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2)
    private String name;

    @Size(min = 3, max = 30)
    private String username;

    @Size(min = 6)
    private String password;

    @Email
    private String email;

    private String phoneNumber;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Discount> discounts;

//    private boolean active;

//    private String roles;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public void addDiscount(Discount discount) {
        if (discounts == null) {
            discounts = new HashSet<Discount>();
        }

        discounts.add(discount);
    }

    public void removeDiscount(Discount discount) {
        if (discounts.isEmpty()) {
            return;
        }

        discounts.remove(discount);
    }

    public void removeAllDiscounts() {
        discounts.clear();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
//                ", roles=" + Arrays.toString(roles.toArray()) +
                ", roles=" + (roles == null ? "null" : Arrays.toString(roles.toArray())) +
                ", discounts=" + (discounts == null ? "null" : Arrays.toString(discounts.toArray())) +
                '}';
    }
}
