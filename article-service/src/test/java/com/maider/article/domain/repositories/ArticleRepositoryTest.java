package com.maider.article.domain.repositories;

import com.maider.article.articleFactory.ArticleFactory;
import com.maider.article.domain.entities.Article;
import com.maider.article.domain.entities.ArticleBuilder;
import com.maider.article.domain.entities.ArticleFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    JpaRepository<Article,Long> jparepo;

    @Autowired
    private ArticleRepository articleRepository;

    @AfterEach
    void clearDataBase() {
        jparepo.deleteAll();
    }
    @Test
    void shouldSaveAnArticle() {
        Article article = ArticleFactory.createOne();

        Article newArticle = articleRepository.save(article);

        assertNotNull(newArticle);
    }
    @Test
    void shouldUpdateOneExistingArticle() {
        Article article = ArticleFactory.createOne();
        Article newArticle = jparepo.save(article);
        Article articleToUpdate = new ArticleBuilder().from(newArticle).withPrice(100).build();
        articleToUpdate.setId(article.getId());

        Article articleUpdated = articleRepository.save(articleToUpdate);

        assertEquals(100, articleUpdated.getPrice());
    }
   @Test
   void shouldRetrievesAllArticles() {
       List<Article> articles = ArticleFactory.create(4);
       jparepo.saveAll(articles);

       List<Article> result = articleRepository.findAll();

       assertEquals(4, result.size());
    }
    @Test
    void  shouldGetOne() {
        Article article = ArticleFactory.createOne();
        Article newArticle = jparepo.save(article);

        Article result = articleRepository.getReferenceById(newArticle.getId());

        assertEquals(newArticle.getId(), result.getId());
    }
    @Test
    void shouldDeleteOne() {
        Article article = ArticleFactory.createOne();
        Article newArticle = jparepo.save(article);

        articleRepository.deleteById(newArticle.getId());
        boolean exist = jparepo.existsById(newArticle.getId());

        assertFalse(exist);
    }
    @Test
    void shouldFilterDependingOnGivenFilters() {
        ArticleBuilder builder = new ArticleBuilder();
        List<Article> articles = new ArrayList<>();
        articles.add(builder.withBrand("Lewis").withPrice(80).withSize(40).withMaterial("cotton").withType("trousers").build()) ;
        articles.add(builder.withBrand("Lewis").withPrice(60).withSize(39).withMaterial("leather").withType("trousers").build());
        articles.add(builder.withBrand("Batela").withPrice(70).withSize(40).withMaterial("leather").withType("trousers").build());
        articles.add(builder.withBrand("Batela").withPrice(90).withSize(39).withMaterial("cotton").withType("trousers").build());
        articles.add(builder.withBrand("Lewis").withPrice(40).withSize(37).withMaterial("elastane").withType("T-shirt").build());
        articles.add(builder.withBrand("Lewis").withPrice(55).withSize(38).withMaterial("cotton").withType("T-shirt").build());
        articles.add(builder.withBrand("Batela").withPrice(45).withSize(40).withMaterial("elastane").withType("T-shirt").build());
        articles.add(builder.withBrand("Batela").withPrice(60).withSize(39).withMaterial("cotton").withType("T-shirt").build());
        jparepo.saveAll(articles);

        ArticleFilter brandFilter = new ArticleFilter(null, null, null, null, "Batela", null, null);
        List<Article> resultBrandFilter = articleRepository.filter(brandFilter);
        ArticleFilter typeFilter = new ArticleFilter("trousers", null, null, null, null, null, null) ;
        List<Article> resultTypeFilter = articleRepository.filter(typeFilter);
        ArticleFilter materialFilter = new ArticleFilter(null, null, null, "cotton", null, null, null) ;
        List<Article> resultmaterialFilter = articleRepository.filter(materialFilter);
        ArticleFilter sizeLessFilter = new ArticleFilter(null, 39, null, null, null, null, null) ;
        List<Article> resultsizeLessFilter = articleRepository.filter(sizeLessFilter);
        ArticleFilter priceLessFilter = new ArticleFilter(null, null, null, null, null, 60.0, null) ;
        List<Article> resultpriceLessFilter = articleRepository.filter(priceLessFilter);

        ArticleFilter allFilters = new ArticleFilter("T-shirt", 39, null, "cotton", "Lewis", null, 50.0);
        List<Article> resullAllFilters = articleRepository.filter(allFilters);

        assertEquals(4, resultBrandFilter.size());
        assertEquals("Batela", resultBrandFilter.get(0).getBrand());
        assertEquals(4, resultBrandFilter.size());
        assertEquals("trousers", resultTypeFilter.get(0).getType());
        assertEquals(4, resultmaterialFilter.size());
        assertEquals("cotton", resultmaterialFilter.get(0).getMaterial());
        assertEquals(5, resultpriceLessFilter.size());
        assertEquals(5, resultsizeLessFilter.size());
        assertTrue(resultsizeLessFilter.get(0).getSize_() == 38 || resultsizeLessFilter.get(0).getSize_() == 39);

        assertEquals(1, resullAllFilters.size());
        assertEquals(55, resullAllFilters.get(0).getPrice());
    }

}