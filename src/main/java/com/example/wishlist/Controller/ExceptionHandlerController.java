package com.example.wishlist.Controller;

import com.example.wishlist.Exceptions.WishItemAlreadyExist;
import com.example.wishlist.Exceptions.WishlistDoesntExist;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(WishlistDoesntExist.class)
    public ResponseEntity<String> getResponseIfWishlistDoesntExist(WishlistDoesntExist ex){
        return new ResponseEntity<>(ex.getMessage(),new HttpHeaders(), HttpStatus.NOT_FOUND);
        //return ResponseEntity.notFound().header(HttpHeaders.Re,ex.getMessage()).build();
    }

    @ExceptionHandler(WishItemAlreadyExist.class)
    public ResponseEntity<String> getResponseIfWishItemExist(WishItemAlreadyExist ex) {
        return new ResponseEntity<>(ex.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> getResponseIfBadCredentials(BadCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(),new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
