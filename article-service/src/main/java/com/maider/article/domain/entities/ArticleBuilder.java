package com.maider.article.domain.entities;

public class ArticleBuilder {
    private String type;
    private int size;
    private String material;
    private String brand;
    private double price;

    public ArticleBuilder withType (String type) {
        this.type = type;
        return this;
    }
    public ArticleBuilder withSize (int size) {
        this.size = size;
        return this;
    }
    public ArticleBuilder withMaterial (String material) {
        this.material = material;
        return this;
    }
    public ArticleBuilder withBrand (String brand) {
        this.brand = brand;
        return this;
    }
    public ArticleBuilder withPrice (double price) {
        this.price = price;
        return this;
    }
    public Article build () {
        return new Article(this.type, this.size, this.material, this.brand, this.price);
    }
    public ArticleBuilder from (Article article) {
        this.price = article.getPrice();
        this.brand = article.getBrand();
        this.size = article.getSize_();
        this.type = article.getType();
        this.material = article.getMaterial();
        return this;
    }
}
