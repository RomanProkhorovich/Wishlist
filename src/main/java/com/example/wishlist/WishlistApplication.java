package com.example.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication
public class WishlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishlistApplication.class, args);
    }

}
