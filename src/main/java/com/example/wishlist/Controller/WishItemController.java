package com.example.wishlist.Controller;

import com.example.wishlist.Model.WishItem;
import com.example.wishlist.Service.WishItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/items")
public class WishItemController {
    private final WishItemService service;

    public WishItemController(WishItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishItem> findById(@PathVariable Long id){
        return ResponseEntity.of(service.findById(id));
    }


    @GetMapping
    public ResponseEntity<List<WishItem>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
