package ee.taltech.webpage.model;

import ee.taltech.webpage.repository.ItemCountRepository;
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
    private List<ItemCount> shoppingCart = new ArrayList<>();

    public void addToWishlist(Item item){
        if (!wishlist.contains(item)){
            wishlist.add(item);
        }
    }

    public void removeFromWishlist(Item item){
        wishlist.remove(item);
    }

    public void clearWishlist(){
        wishlist.clear();
    }

    public void addItemToShoppingCart(Item item, ItemCountRepository itemCountRepository){
        if (shoppingCart.stream().filter(x->x.getItem().equals(item)).findFirst().isEmpty()) {
            ItemCount itemCount = new ItemCount(item, 1);
            itemCountRepository.save(itemCount);
            shoppingCart.add(itemCount);
        } else {
            shoppingCart.stream().filter(x->x.getItem().equals(item)).findFirst().get()
                    .setQuantity(shoppingCart.stream().filter(x->x.getItem().equals(item))
                            .findFirst().get().getQuantity() + 1);
            itemCountRepository.save(shoppingCart.stream().filter(x->x.getItem().equals(item)).findFirst().get());
        }
    }

    public void removeItemFromShoppingCart(Item item, ItemCountRepository itemCountRepository){
        if (shoppingCart.stream().anyMatch(x->x.getItem().equals(item))
                && shoppingCart.stream().filter(x->x.getItem().equals(item)).findFirst().get().getQuantity() > 0){
            ItemCount itemCount = shoppingCart.stream().filter(x->x.getItem().equals(item)).findFirst().get();
            itemCount.setQuantity(shoppingCart.stream().filter(x->x.getItem().equals(item))
                            .findFirst().get().getQuantity() - 1);
            itemCountRepository.save(itemCount);
            if (itemCount.getQuantity() == 0) {
                shoppingCart.remove(itemCount);
                itemCountRepository.delete(itemCount);
            }
        }
    }

    public void clearShoppingCart(){
        shoppingCart.clear();
    }
}
