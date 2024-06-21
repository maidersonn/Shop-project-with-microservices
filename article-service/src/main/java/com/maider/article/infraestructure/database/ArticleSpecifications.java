package com.maider.article.infraestructure.database;

import com.maider.article.domain.entities.Article;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ArticleSpecifications implements Specification<Article> {
    private String type;
    private Integer sizeLessThan;
    private Integer sizeGreaterThan;
    private String material;
    private String brand;
    private Double priceLessThan;
    private Double priceGreaterThan;

    public ArticleSpecifications(String type, Integer sizeLessThan, Integer sizeGreaterThan, String material, String brand, Double priceLessThan, Double priceGreaterThan) {
        this.type = type;
        this.sizeLessThan = sizeLessThan;
        this.sizeGreaterThan = sizeGreaterThan;
        this.material = material;
        this.brand = brand;
        this.priceLessThan = priceLessThan;
        this.priceGreaterThan = priceGreaterThan;
    }

    @Override
    public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(type != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if(sizeLessThan != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("size_"), sizeLessThan));
        }
        if(sizeGreaterThan != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("size_"), sizeGreaterThan));
        }
        if(material != null) {
            predicates.add(criteriaBuilder.equal(root.get("material"), material));
        }
        if(brand != null) {
            predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
        }
        if(priceLessThan != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceLessThan));
        }
        if(priceGreaterThan != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceGreaterThan));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
