package com.maider.article.result;

public class Success<V,E> implements Result<V,E> {

    private final V value;

    public Success(V value) {
        this.value = value;
    }

    @Override
    public Boolean isSuccess() {
        return Boolean.TRUE;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public  E getError() {
        return null;
    }
}
