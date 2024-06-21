package com.maider.article.infraestructure.controllers;

import com.maider.article.domain.entities.dto.FilterDTO;
import com.maider.article.infraestructure.mappers.ArticleFilterMapper;
import com.maider.article.domain.entities.Article;
import com.maider.article.domain.entities.ArticleFilter;
import com.maider.article.domain.services.errors.ShopError;
import com.maider.article.result.Result;
import com.maider.article.result.Success;
import com.maider.article.domain.services.ArticleService;
import com.maider.article.domain.entities.dto.ArticleDTO;
import com.maider.article.domain.entities.dto.ArticleCreationDTO;
import com.maider.article.infraestructure.mappers.ArticleMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleFilterMapper filterMapper;

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<?> getAll() {
        Result<List<Article>, String> articles = articleService.getAll();
        if(articles instanceof Success<?,?>) {
            List<Article> articlesList = articles.getValue();
            List<ArticleDTO> articleDTOList = articlesList.stream().map(ArticleMapper::toDto).toList();
            return new ResponseEntity<>(articleDTOList,  HttpStatus.OK);
        }
        return new ResponseEntity<>(articles.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/article")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody @Valid ArticleCreationDTO articleDTO) {
        Article newArticle = ArticleMapper.toArticle(articleDTO);
        Result<Article, String> article = articleService.save(newArticle);
        if (article instanceof Success<Article, String>) {
            ArticleDTO newArticleDTO = ArticleMapper.toDto(article.getValue());
            return new ResponseEntity<>(newArticleDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(article.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Result<Boolean, String> deleteResponse = articleService.deleteById(id);
        if (deleteResponse instanceof Success<Boolean, String>) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deleteResponse.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/article/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ArticleCreationDTO  articleDTO,
                                    @PathVariable Long id) {
        Article articleToUpdate = ArticleMapper.toArticle(articleDTO);
        Result<Article, String> updatedArticle = articleService.updateById(id, articleToUpdate);
        if (updatedArticle instanceof Success<Article, String>) {
            ArticleDTO updatedArticleDTO = ArticleMapper.toDto(updatedArticle.getValue());
            return new ResponseEntity<>(updatedArticleDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedArticle.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/articles/filtered")
    public ResponseEntity<?> getFiltered(FilterDTO filterdto) {
        ArticleFilter filters = filterMapper.toArticleFilter(filterdto);
        Result<List<Article>, String> filteredArticles = articleService.getFiltered(filters);
        if(filteredArticles instanceof Success<List<Article>, String>) {
            List<ArticleDTO> filteredArticlesDTO = filteredArticles.getValue().stream().map(ArticleMapper::toDto).toList();
            return new ResponseEntity<>(filteredArticlesDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(filteredArticles.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("article/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Result<Article, ShopError> article = articleService.getById(id);
        if (article instanceof Success<Article, ShopError>) {
            ArticleDTO articleDTO = ArticleMapper.toDto(article.getValue());
            return new ResponseEntity<>(articleDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(article.getError().getMessage(), HttpStatus.NOT_FOUND);
    }
}
