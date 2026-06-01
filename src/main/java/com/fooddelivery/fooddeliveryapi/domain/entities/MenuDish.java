package com.fooddelivery.fooddeliveryapi.domain.entities;

import com.fooddelivery.fooddeliveryapi.enums.MenuDishStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "menu_dishes")
public class MenuDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_dish_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_price", nullable = false)
    private Double price;

    @Column(name = "veg", nullable = false)
    private Boolean veg;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "available", nullable = false)
    private boolean available;

    @Column(name = "status", nullable = false)
    private MenuDishStatus status;

    @Column(name = "discontinued_time")
    private LocalDateTime discontinuedTime;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public MenuDish() {
    }

    public MenuDish(Long id, String name, Double price, Boolean veg, Restaurant restaurant, Boolean available, MenuDishStatus status, LocalDateTime discontinuedTime, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.veg = veg;
        this.restaurant = restaurant;
        this.available = available;
        this.status = status;
        this.discontinuedTime = discontinuedTime;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public MenuDishStatus getStatus() {
        return status;
    }

    public void setStatus(MenuDishStatus status) {
        this.status = status;
    }

    public LocalDateTime getDiscontinuedTime() {
        return discontinuedTime;
    }

    public void setDiscontinuedTime(LocalDateTime discontinuedTime) {
        this.discontinuedTime = discontinuedTime;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuDish menuDish = (MenuDish) o;
        return Objects.equals(id, menuDish.id) && Objects.equals(name, menuDish.name) && Objects.equals(price, menuDish.price) && Objects.equals(veg, menuDish.veg) && Objects.equals(restaurant, menuDish.restaurant) && Objects.equals(available, menuDish.available) && status == menuDish.status && Objects.equals(discontinuedTime, menuDish.discontinuedTime) && Objects.equals(created, menuDish.created) && Objects.equals(updated, menuDish.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, veg, restaurant, available, status, discontinuedTime, created, updated);
    }

    @Override
    public String toString() {
        return "MenuDish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", veg=" + veg +
                ", restaurant=" + restaurant +
                ", available=" + available +
                ", status=" + status +
                ", discontinuedTime=" + discontinuedTime +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}