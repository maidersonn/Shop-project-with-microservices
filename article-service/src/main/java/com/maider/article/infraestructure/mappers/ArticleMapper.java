package com.maider.article.infraestructure.mappers;

import com.maider.article.domain.entities.Article;
import com.maider.article.domain.entities.dto.ArticleCreationDTO;
import com.maider.article.domain.entities.dto.ArticleDTO;

public class ArticleMapper {
    public static ArticleDTO toDto (Article article) {
        String type = article.getType();
        String material = article.getMaterial();
        String name = material.concat(type);
        return new ArticleDTO(article.getId(), name, article.getBrand(), article.getSize_(), article.getPrice());
    }
    public static Article toArticle (ArticleCreationDTO articleDTO) {
        return new Article(articleDTO.getType(), articleDTO.getSize(), articleDTO.getMaterial(), articleDTO.getBrand(), articleDTO.getPrice());
    }
}
