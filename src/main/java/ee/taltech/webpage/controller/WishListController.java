//package ee.taltech.webpage.controller;
//
//import ee.taltech.webpage.items.Item;
//import ee.taltech.webpage.service.WishListService;
//import ee.taltech.webpage.wishlists.WishList;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RequestMapping("wishlist")
//@RestController
//public class WishListController {
//
//    @Autowired
//    private WishListService wishListService;
//
//    @GetMapping()
//    public List<Item> getWishList() {
//        return wishListService.getWishList();
//    }
//}
