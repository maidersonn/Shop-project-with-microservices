package com.maider.article.result;

public interface Result<TValue, TError> {

    Boolean isSuccess();
    TValue getValue();
    TError getError();

}
