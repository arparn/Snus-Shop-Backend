package ee.taltech.webpage.model;

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

    public List<Item> getWishlist(){
        return wishlist;
    }

//    public void addItemToWishList(Item item){
//        if (!items.contains(item)){
//            items.add(item);
//        }
//    }
//
//    public void removeItemFromWishList(Item item){
//        if (items.contains(item)){
//            items.remove(item);
//        }
//    }
//
//    public void clearWishList(){
//        items.clear();
//    }
//
//    public List<Item> getWishList(){
//        return items;
//    }
}
