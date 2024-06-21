package com.maider.article.domain.repositories;

import com.maider.article.domain.entities.Article;
import com.maider.article.domain.entities.ArticleFilter;

import java.util.List;

public interface ArticleRepository {

    public List<Article> filter(ArticleFilter filters);
    public Article save(Article article);
    public List<Article> findAll();
    public void deleteById(Long id);
    Boolean existsById(Long id);
    Article getReferenceById(Long id);
}
