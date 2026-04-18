package com.fooddelivery.fooddeliveryapi.domain.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_age", nullable = true)
    private Integer age;

    @Column(name = "customer_address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> order;

    public Customer() {
    }

    public Customer(Long id, String name, Integer age, String address, List<Order> order) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.order = order;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(age, customer.age) && Objects.equals(address, customer.address) && Objects.equals(order, customer.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address, order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", order=" + order +
                '}';
    }
}
