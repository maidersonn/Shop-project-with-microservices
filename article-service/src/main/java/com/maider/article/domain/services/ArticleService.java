package com.maider.article.domain.services;

import com.maider.article.domain.entities.Article;
import com.maider.article.domain.entities.ArticleFilter;
import com.maider.article.domain.repositories.ArticleRepository;
import com.maider.article.domain.services.errors.ShopError;
import com.maider.article.domain.services.errors.ArticleNotFoundError;
import com.maider.article.result.Failure;
import com.maider.article.result.Result;
import com.maider.article.result.Success;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Result<Article, String> save(Article article) {
       try {
           Article newArticle = articleRepository.save(article);
           return new Success<>(newArticle);
       } catch (Exception e) {
           return new Failure<>("Error relationed with database");
       }

    }
    public Result<List<Article>, String> getAll() {
        try {
            List<Article> articles = articleRepository.findAll();
            return new Success<>(articles);
        } catch (Exception e) {
            return new Failure<>("Error retrieving articles");
        }
    }

    public Result<Boolean, String> deleteById(Long id) {
        try {
            articleRepository.deleteById(id);
            return new Success<>(true);
        } catch (Exception e) {
            return new Failure<>("Error deleting article");
        }
    }

    public Result<Article, String> updateById(Long id, Article articleToUpdate) {
        try {
            Boolean article = articleRepository.existsById(id);
            if(!article) return new Failure<>("This article does not exist");
            articleToUpdate.setId(id);
            Article updatedArticle = articleRepository.save(articleToUpdate);
            return new Success<>(updatedArticle);
        } catch (Exception e) {
            return new Failure<>("Error updating article");
        }
    }
    public Result<List<Article>, String> getFiltered(ArticleFilter filters) {
        try {
            List<Article> articles = articleRepository.filter(filters);
            return new Success<>(articles);
        } catch (Exception e) {
            return new Failure<>("Error retrieving filtered articles");
        }

    }

    public Result<Article, ShopError> getById(Long id) {
        try {
            Article article = articleRepository.getReferenceById(id);
            return new Success<>(article);
        } catch (EntityNotFoundException e) {
            return new Failure<>(new ArticleNotFoundError("Article not found"));
        }
    }
}
