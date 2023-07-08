package com.example.wishlist.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.ast.tree.expression.Collation;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long wishlist_id;
    private Date creationDate;
    private Date holiday;
    private String name;



    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "items",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "items_id"))
    private Set<WishItem> items = new LinkedHashSet<>();


    public void add(WishItem item){
        var b= new LinkedHashSet<>(getItems());
        b.add(item);
        setItems(b);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return Objects.equals(wishlist_id, wishlist.wishlist_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlist_id);
    }
}