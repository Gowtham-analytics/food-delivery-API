package com.fooddelivery.fooddeliveryapi.domain.entities;

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

    @Column(name = "total_price", nullable = false, updatable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDate orderDate;

    @Column(name = "order_time", nullable = false, updatable = false)
    private LocalTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    public Order() {
    }

    public Order(Long id, Double totalPrice, List<OrderItem> orderItemsList, Customer customer, Restaurant restaurant, LocalDate orderDate, LocalTime orderTime, OrderStatus status) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderItemsList = orderItemsList;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.status = status;
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

    public List<OrderItem> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItem> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(orderItemsList, order.orderItemsList) && Objects.equals(customer, order.customer) && Objects.equals(restaurant, order.restaurant) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderTime, order.orderTime) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, orderItemsList, customer, restaurant, orderDate, orderTime, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", orderItemsList=" + orderItemsList +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", status=" + status +
                '}';
    }
}
