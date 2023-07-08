package com.example.wishlist.Controller;

import com.example.wishlist.Exceptions.WishItemAlreadyExist;
import com.example.wishlist.Exceptions.WishlistDoesntExist;
import com.example.wishlist.Model.WishItem;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.WishlistRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlists")
public class WishListController {
    private final WishlistRepository repo;

    public WishListController(WishlistRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> findById(@PathVariable Long id){
        return ResponseEntity.of(repo.findById(id));
    }

    @PutMapping("/{listId}")
    public ResponseEntity<Wishlist> addItem(@PathVariable Long listId,@RequestBody WishItem item){
        var listOptional=repo.findById(listId);
        if (listOptional.isEmpty())
            throw new WishlistDoesntExist(String.valueOf(listId));
        var list=listOptional.get();
        list.add(item);
        return ResponseEntity.ok(repo.save(list));
    }

    @PostMapping
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Wishlist list){
        if(list.getWishlist_id()==null || repo.findById(list.getWishlist_id()).isEmpty())
            throw new WishItemAlreadyExist(list.getWishlist_id());
        return  ResponseEntity.ok(repo.save(list));
    }
}
