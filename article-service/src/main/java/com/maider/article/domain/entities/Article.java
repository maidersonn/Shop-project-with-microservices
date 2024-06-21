package com.maider.article.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int size_;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return size_ == article.size_ && Double.compare(article.price, price) == 0 && Objects.equals(id, article.id) && Objects.equals(type, article.type) && Objects.equals(material, article.material) && Objects.equals(brand, article.brand);
    }
    private String material;
    private String brand;
    private double price;
    public Article(String type, int size, String material, String brand, double price) {
        this.type = type;
        this.size_ = size;
        this.material = material;
        this.brand = brand;
        this.price = price;
    }
    public Article() { }
    public Long getId() { return this.id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public int getSize_() {
        return size_;
    }
    public String getMaterial() {
        return material;
    }
    public String getBrand() {
        return brand;
    }
    public double getPrice() {
        return price;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setSize_(int size_) {
        this.size_ = size_;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
