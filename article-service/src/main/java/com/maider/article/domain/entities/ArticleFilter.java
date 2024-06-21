package com.maider.article.domain.entities;

import java.util.Objects;

public class ArticleFilter {
    private String type;
    private Integer sizeLessThan;
    private Integer sizeGreaterThan;
    private String material;
    private String brand;
    private Double priceLessThan;
    private Double priceGreaterThan;

    public ArticleFilter(String type, Integer sizeLessThan, Integer sizeGreaterThan, String material, String brand, Double priceLessThan, Double priceGreaterThan) {
        this.type = type;
        this.sizeLessThan = sizeLessThan;
        this.sizeGreaterThan = sizeGreaterThan;
        this.material = material;
        this.brand = brand;
        this.priceLessThan = priceLessThan;
        this.priceGreaterThan = priceGreaterThan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleFilter that = (ArticleFilter) o;
        return Objects.equals(type, that.type) && Objects.equals(sizeLessThan, that.sizeLessThan) && Objects.equals(sizeGreaterThan, that.sizeGreaterThan) && Objects.equals(material, that.material) && Objects.equals(brand, that.brand) && Objects.equals(priceLessThan, that.priceLessThan) && Objects.equals(priceGreaterThan, that.priceGreaterThan);
    }

    public String getType() {
        return type;
    }

    public Integer getSizeLessThan() {
        return sizeLessThan;
    }

    public Integer getSizeGreaterThan() {
        return sizeGreaterThan;
    }

    public String getMaterial() {
        return material;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPriceLessThan() {
        return priceLessThan;
    }

    public Double getPriceGreaterThan() {
        return priceGreaterThan;
    }
}
