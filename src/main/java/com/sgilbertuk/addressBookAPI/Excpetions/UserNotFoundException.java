package com.sgilbertuk.addressBookAPI.Excpetions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage, Throwable cause){
        super(errorMessage,cause);
    }

}
