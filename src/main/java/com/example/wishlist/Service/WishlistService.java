package com.example.wishlist.Service;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wishlist;
import com.example.wishlist.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WishlistService {
    private final WishlistRepository repo;

    public WishlistService(WishlistRepository repo) {
        this.repo = repo;
    }

    public Wishlist save(Wishlist list){
        return repo.save(list);
    }

    public Optional<Wishlist> findById(Long id){
        return  repo.findById(id);
    }

    public List<Wishlist> findAll(){
        return repo.findAll();
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }


    public Set<Wishlist> findAllByUser(User user){
        return repo.findAllByUser(user);
    }


}
