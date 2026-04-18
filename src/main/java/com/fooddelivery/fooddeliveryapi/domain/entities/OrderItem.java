package com.fooddelivery.fooddeliveryapi.domain.entities;

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

    @Column(name = "order_price")
    private Double orderPrice;

    public OrderItem() {
    }

    public OrderItem(Long id, MenuDish menuDish, Order order, int quantity, Double orderPrice) {
        this.id = id;
        this.menuDish = menuDish;
        this.order = order;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
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
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(id, orderItem.id) && Objects.equals(menuDish, orderItem.menuDish) && Objects.equals(order, orderItem.order) && Objects.equals(orderPrice, orderItem.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuDish, order, quantity, orderPrice);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", menuDish=" + menuDish +
                ", order=" + order +
                ", quantity=" + quantity +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
