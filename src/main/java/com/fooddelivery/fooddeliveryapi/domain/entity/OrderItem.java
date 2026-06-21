package com.fooddelivery.fooddeliveryapi.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_dish_id", nullable = false, updatable = false)
    private MenuDish menuDish;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private Order order;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    public OrderItem() {
    }

    public OrderItem(Long id, MenuDish menuDish, Order order, int quantity, Double totalPrice) {
        this.id = id;
        this.menuDish = menuDish;
        this.order = order;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuDish getMenuDish() {
        return menuDish;
    }

    public void setMenuDish(MenuDish menuDish) {
        this.menuDish = menuDish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getOrderPrice() {
        return totalPrice;
    }

    public void setOrderPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(id, orderItem.id) && Objects.equals(menuDish, orderItem.menuDish) && Objects.equals(order, orderItem.order) && Objects.equals(totalPrice, orderItem.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuDish, order, quantity, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", menuDish=" + menuDish +
                ", order=" + order +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
