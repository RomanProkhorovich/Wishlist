package com.example.wishlist.Controller;

import com.example.wishlist.Exceptions.WishItemAlreadyExist;
import com.example.wishlist.Exceptions.WishlistDoesntExist;
import com.example.wishlist.Model.User;
import com.example.wishlist.Model.WishItem;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.WishlistRepository;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/wishlists")
public class WishListController {

    //TODO: create a new controller for items

    private final WishlistService repo;
    private final UserService userService;

    public WishListController(WishlistService repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Set<Wishlist>> findAll(@AuthenticationPrincipal UserDetails details){
       User user = initUser(details);

        return ResponseEntity.ok(repo.findAllByUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> findById(@PathVariable Long id,
                                             @AuthenticationPrincipal UserDetails details){

        User user = initUser(details);

        return ResponseEntity.ok(repo.findAllByUser(user).stream()
                .filter(x->x.getWishlist_id().equals(id)).findFirst()
                .orElseThrow(()->new WishlistDoesntExist("wishlist not fond")));
    }

    @PutMapping("/{listId}")
    public ResponseEntity<Wishlist> addItem(@PathVariable Long listId,
                                            @RequestBody WishItem item,
                                            @AuthenticationPrincipal UserDetails details){

        User user = initUser(details);

        var usersList=repo.findAllByUser(user).stream()
                .filter(x->x.getWishlist_id()
                .equals(listId)).findFirst().orElseThrow(()->new WishlistDoesntExist(String.valueOf(listId)));
        usersList.addItem(item);
        return  ResponseEntity.ok(repo.save(usersList));
    }



    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist list,
                                                   @AuthenticationPrincipal UserDetails details){
        User user=initUser(details);
        list.setUser(user);

        if(list.getWishlist_id()==null || repo.findById(list.getWishlist_id()).isEmpty())
            throw new WishItemAlreadyExist(list.getWishlist_id());
        return  ResponseEntity.ok(repo.save(list));
    }


    private User initUser(UserDetails details) {
        return userService.findByUsername(details.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}
