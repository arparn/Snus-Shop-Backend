package ee.taltech.webpage.model;

import ee.taltech.webpage.repository.ItemsRepository;
import ee.taltech.webpage.repository.UserRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {


    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    private List<Item> wishlist = new ArrayList<>();
    @ManyToMany
    private List<Item> shoppingCart = new ArrayList<>();

    public void addAndRemoveWishlist(Item item){
        if (!wishlist.contains(item)) {
            wishlist.add(item);
        }  else {
            wishlist.remove(item);
        }
    }

    public void clearWishlist(){
        wishlist.clear();
    }

    public void addItemToShoppingCart(Item item){
        if (!shoppingCart.contains(item)){
            shoppingCart.add(item);
        }
    }

    public void removeItemFromShoppingCart(Item item){
        shoppingCart.remove(item);
    }

    public void clearShoppingCart(){
        shoppingCart.clear();
    }
}
