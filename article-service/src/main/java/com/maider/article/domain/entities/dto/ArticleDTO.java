package com.maider.article.domain.entities.dto;

import java.io.Serializable;

public class ArticleDTO implements Serializable {
    private Long id;
    private String name; // type + material. example : cotton trousers
    private String brand;
    private int size;
    private double price;

    public ArticleDTO(Long id, String name, String brand, int size, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
