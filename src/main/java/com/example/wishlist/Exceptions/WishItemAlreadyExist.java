package com.example.wishlist.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Item already exist")
public class WishItemAlreadyExist extends RuntimeException {
    public WishItemAlreadyExist(Long id) {
        super("Item with  id="+id+" already exist");
    }
}
