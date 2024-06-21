package com.maider.article.domain.services.errors;

public class ArticleNotFoundError implements ShopError {

    private String message;

    public ArticleNotFoundError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
