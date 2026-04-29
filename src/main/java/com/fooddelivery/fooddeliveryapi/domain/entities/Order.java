package com.fooddelivery.fooddeliveryapi.domain.entities;

import com.fooddelivery.fooddeliveryapi.enums.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "number_of_items")
    private Integer numberOfItems;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_time")
    private LocalTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(Long id, Double totalPrice, Integer numberOfItems, List<OrderItem> orderItemsList, Restaurant restaurant, UserEntity userEntity, LocalDate orderDate, LocalTime orderTime, OrderStatus orderStatus) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.numberOfItems = numberOfItems;
        this.orderItemsList = orderItemsList;
        this.restaurant = restaurant;
        this.userEntity = userEntity;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNumberOfItems() { return numberOfItems; }

    public void setNumberOfItems(int numberOfItems) { this.numberOfItems = numberOfItems; }

    public List<OrderItem> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItem> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return numberOfItems == order.numberOfItems && Objects.equals(id, order.id) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(orderItemsList, order.orderItemsList) && Objects.equals(restaurant, order.restaurant) && Objects.equals(userEntity, order.userEntity) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderTime, order.orderTime) && orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, numberOfItems, orderItemsList, restaurant, userEntity, orderDate, orderTime, orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", numberOfItems=" + numberOfItems +
                ", orderItemsList=" + orderItemsList +
                ", restaurant=" + restaurant +
                ", userEntity=" + userEntity +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
