package com.example.wishlist.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long items_id;
    private double price;
    private String name;
    private String link;
    private String description;
    private String photoLink;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishItem wishItem = (WishItem) o;
        return Objects.equals(items_id, wishItem.items_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items_id);
    }
}
