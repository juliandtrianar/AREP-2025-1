package com.example.accessingdatajpa.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EntityDatos {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)


    private Long id;

    private  String address;
    private Integer size;
    private  Integer price;
    private   String description;
    public EntityDatos() {}
    public EntityDatos(String address,Integer size,Integer price ,String description) {
        this.address =address;
        this.size=size;
        this.price=price;
        this.description=description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
