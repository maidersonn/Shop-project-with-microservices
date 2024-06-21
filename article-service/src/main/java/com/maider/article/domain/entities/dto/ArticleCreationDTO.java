package com.maider.article.domain.entities.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ArticleCreationDTO {

    @NotBlank
    private String type;
    @NotBlank
    private String material;
    @NotBlank
    private String brand;
    @NotNull
    @Min(value=0)
    private Integer size;
    @NotNull
    @Min(value=0)
    private Double price;

    public ArticleCreationDTO(String type, String material, String brand, Integer size, Double price) {
        this.type = type;
        this.material = material;
        this.brand = brand;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getSize() {
        return size;
    }

    public Double getPrice() {
        return price;
    }
}
