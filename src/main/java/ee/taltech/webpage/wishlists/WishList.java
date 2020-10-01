//package ee.taltech.webpage.wishlists;
//
//import ee.taltech.webpage.items.Item;
//
//import javax.persistence.*;
//import java.util.LinkedList;
//import java.util.List;
//
//@Entity
//public class WishList {
//
//    @ManyToOne
//    @Column(name="items")
//    private List<Item> items = new LinkedList<>();
//
//    public WishList(List<Item> items){
//        this.items = items;
//    }
//
//    public WishList(){
//    }
//
//    public WishList(Item item){
//        items.add(item);
//    }
//
//    public List<Item> getWishList(){
//        return items;
//    }
//
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
//}
