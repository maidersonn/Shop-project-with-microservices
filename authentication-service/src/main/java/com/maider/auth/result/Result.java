package com.maider.auth.result;

public interface Result<TValue, TError> {

    Boolean isSuccess();
    TValue getValue();
    TError getError();

}
