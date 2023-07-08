package com.example.wishlist.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such wishlist")
public class WishlistDoesntExist extends RuntimeException {

    public WishlistDoesntExist(String message) {
        super("Wishlist with id="+message+" doesnt exist");
    }
}
