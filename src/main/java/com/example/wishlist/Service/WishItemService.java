package com.example.wishlist.Service;

import com.example.wishlist.Model.WishItem;
import com.example.wishlist.Repository.WishItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishItemService {
    private final WishItemRepository repo;

    public WishItemService(WishItemRepository repo) {
        this.repo = repo;
    }

    public WishItem save(WishItem item){
        return repo.save(item);
    }

    public List<WishItem> findAll(){return repo.findAll();}

    public Optional<WishItem> findById(Long id){return repo.findById(id);}
}
