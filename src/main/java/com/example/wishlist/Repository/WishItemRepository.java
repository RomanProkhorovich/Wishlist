package com.example.wishlist.Repository;

import com.example.wishlist.Model.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishItemRepository extends JpaRepository<WishItem,Long> {
}
