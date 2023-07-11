package com.example.wishlist.Repository;

import com.example.wishlist.Model.User;
import com.example.wishlist.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
     Set<Wishlist> findAllByUser(User user);
}
