package com.maider.authenticationMicroService.result;

public class Failure<Nothing, E> implements Result<Nothing, E> {

    private final E errors;

    public Failure(E errors) {
        this.errors = errors;
    }

    @Override
    public Boolean isSuccess() {
        return false;
    }

    @Override
    public Nothing getValue() {
        return null;
    }

    @Override
    public E getError() {
        return this.errors;
    }
}
