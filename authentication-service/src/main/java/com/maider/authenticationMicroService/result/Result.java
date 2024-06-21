package com.maider.authenticationMicroService.result;

public interface Result<TValue, TError> {

    Boolean isSuccess();
    TValue getValue();
    TError getError();

}
