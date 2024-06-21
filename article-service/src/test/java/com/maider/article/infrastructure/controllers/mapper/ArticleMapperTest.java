package com.maider.article.infrastructure.controllers.mapper;

import com.maider.article.domain.entities.dto.ArticleCreationDTO;
import com.maider.article.domain.entities.dto.ArticleDTO;
import com.maider.article.domain.entities.Article;
import com.maider.article.infraestructure.mappers.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleMapperTest {
    @Test
    public void shouldGiveBackArticleDTO() {
        ArticleMapper mapper = new ArticleMapper();
        Article article = new Article("Trouser", 38, "cotton", "Batela", 10.5);

        ArticleDTO articleDTO = mapper.toDto(article);

        assertInstanceOf(ArticleDTO.class, articleDTO);
        assertEquals("cottonTrouser", articleDTO.getName());
        assertEquals(article.getBrand(), articleDTO.getBrand());
        assertEquals(article.getSize_(), articleDTO.getSize());
        assertEquals(article.getPrice(), articleDTO.getPrice());
    }
    @Test
    public void shouldGiveBackArticle() {
        ArticleMapper mapper = new ArticleMapper();
        ArticleCreationDTO articleDTO = new ArticleCreationDTO("Trouser", "cotton", "Batela", 38, 10.5);

        Article article = mapper.toArticle(articleDTO);

        assertInstanceOf(Article.class, article);
        assertEquals(articleDTO.getBrand(), article.getBrand());
        assertEquals(articleDTO.getMaterial(), article.getMaterial());
        assertEquals(articleDTO.getType(), article.getType());
        assertEquals(articleDTO.getSize(), article.getSize_());
        assertEquals(articleDTO.getPrice(), article.getPrice());
    }

}