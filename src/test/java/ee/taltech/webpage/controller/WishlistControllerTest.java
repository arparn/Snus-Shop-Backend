package ee.taltech.webpage.controller;

import ee.taltech.webpage.common.RestTemplateTests;
import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.service.users.dto.RegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistControllerTest extends RestTemplateTests {

    @Test
    void wishlist_add_remove() {
        ResponseEntity<List<Item>> wishlistEx = templateWithAdmin().exchange("/wishlist", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEMS);//TODO
        List<Item> wishlist = wishlistEx.getBody();
        assertNotNull(wishlist);
        assertEquals(HttpStatus.OK, wishlistEx.getStatusCode());
        assertTrue(wishlist.isEmpty());
        ResponseEntity<Item> itemEx = templateWithAdmin().exchange("/wishlist", HttpMethod.POST, entity(3, "admin"), ITEM);//TODO
        Item item = itemEx.getBody();
        assert item != null;
        assertEquals(3, item.getId());
        wishlistEx = templateWithAdmin().exchange("/wishlist", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEMS);//TODO
        wishlist = wishlistEx.getBody();
        assertNotNull(wishlist);
        assertEquals(1, wishlist.size());
        templateWithAdmin().exchange("/wishlist/3", HttpMethod.DELETE, entity(3, "admin"), ITEM);//TODO
        wishlistEx = templateWithAdmin().exchange("/wishlist", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEMS);//TODO
        wishlist = wishlistEx.getBody();
        assertNotNull(wishlist);
        assertTrue(wishlist.isEmpty());
    }
}
