package com.example.wishlist.Controller;

import com.example.wishlist.Exceptions.WishItemAlreadyExist;
import com.example.wishlist.Exceptions.WishlistDoesntExist;
import com.example.wishlist.Model.WishItem;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.WishlistRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
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
        Wishlist list = getWishlist(listId);
        list.addItem(item);
        return ResponseEntity.ok(repo.save(list));
    }



    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist list){
        if(list.getWishlist_id()==null || repo.findById(list.getWishlist_id()).isEmpty())
            throw new WishItemAlreadyExist(list.getWishlist_id());
        return  ResponseEntity.ok(repo.save(list));
    }

    @DeleteMapping("/{listId}/{itemId}")
    public void deleteItem(@PathVariable Long listId,@PathVariable Long itemId){
        var wishlist=getWishlist(listId);
        wishlist.deleteItem(itemId);
        repo.save(wishlist);
    }


    private Wishlist getWishlist(Long listId) {
        var listOptional=repo.findById(listId);
        if (listOptional.isEmpty())
            throw new WishlistDoesntExist(String.valueOf(listId));
        return listOptional.get();
    }
}
