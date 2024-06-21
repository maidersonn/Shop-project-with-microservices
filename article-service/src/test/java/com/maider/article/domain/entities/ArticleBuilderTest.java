package com.maider.article.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ArticleBuilderTest {
    @Test
    public void itCreatesArticleWithProperties () {
        ArticleBuilder builder = new ArticleBuilder();
        builder.withBrand("Lewis")
                .withType("pantalon")
                .withMaterial("vaquero")
                .withSize(38)
                .withPrice(80);
        Article article = builder.build();
        assertEquals("pantalon", article.getType());
        assertEquals(38, article.getSize_());
        assertEquals("vaquero", article.getMaterial());
        assertEquals("Lewis", article.getBrand());
        assertEquals(80, article.getPrice());
    }
}
