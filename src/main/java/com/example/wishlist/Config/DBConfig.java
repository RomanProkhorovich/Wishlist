package com.example.wishlist.Config;

import com.example.wishlist.Model.Security.Role;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.WishItem;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.RoleRepository;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishItemService;
import com.example.wishlist.Service.WishlistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Configuration
public class DBConfig {
    private final WishItemService items;
    private final WishlistService wishlistRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public DBConfig(WishItemService items, WishlistService wishlistRepository, BCryptPasswordEncoder encoder, UserService userService, RoleRepository roleRepository) {
        this.items = items;
        this.wishlistRepository = wishlistRepository;
        this.encoder = encoder;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @Bean
    public void initDB(){
        WishItem item1= WishItem.builder()
                .price(100)
                .name("name1")
                .description("хуй знает")
                .link("пока нет")
                .photoLink("тоже пока нет")
                .photoLink("и ее нет....")
                .build();

        WishItem item2= WishItem.builder()
                .price(10)
                .name("name2")
                .description("хуй знает2")
                .link("пока нет2")
                .photoLink("тоже пока нет2")
                .photoLink("и ее нет2....")
                .build();

        WishItem item3= WishItem.builder()
                .price(101)
                .name("name2")
                .description("хуй знает2")
                .link("пока нет2")
                .photoLink("тоже пока нет2")
                .photoLink("и ее нет2....")
                .build();

        item1=items.save(item1);
        item2=items.save(item2);
        item3=items.save(item3);

        Wishlist list=Wishlist.builder()
                .creationDate( Date.from(Instant.now()))
                .holiday( Date.from(Instant.now()))
                .name("List1")
                .items(Set.of(item1,item2))
                .build();

        list=wishlistRepository.save(list);

        Role role=new Role("ROLE_ADMIN");
        role=roleRepository.save(role);

        userService.save(User.builder()
                .mail("mymail@test.com")
                .roles(Set.of(role))
                .username("Admin")
                .password(encoder.encode("password"))
                .build());


    }
}
