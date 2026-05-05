package com.fooddelivery.fooddeliveryapi.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "restaurant_name", nullable = false, updatable = true)
    private String name;

    @Column(name = "location", nullable = false, updatable = true)
    private String location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MenuDish> menuDish = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Order> order = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String location, List<MenuDish> menuDish, List<Order> order, UserEntity userEntity, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menuDish = menuDish;
        this.order = order;
        this.userEntity = userEntity;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<MenuDish> getMenuDish() {
        return menuDish;
    }

    public void setMenuDish(List<MenuDish> menuDish) {
        this.menuDish = menuDish;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(menuDish, that.menuDish) && Objects.equals(order, that.order) && Objects.equals(userEntity, that.userEntity) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, menuDish, order, userEntity, created, updated);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", menuDish=" + menuDish +
                ", order=" + order +
                ", userEntity=" + userEntity +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
