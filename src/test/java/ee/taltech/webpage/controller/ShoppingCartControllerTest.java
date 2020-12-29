package ee.taltech.webpage.controller;

import ee.taltech.webpage.common.RestTemplateTests;
import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartControllerTest extends RestTemplateTests {

    @Test
    void cart_add_remove() {
        ResponseEntity<List<ItemCount>> cartEx = templateWithAdmin().exchange("/cart", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEM_COUNT);//TODO
        List<ItemCount> cart = cartEx.getBody();
        assertNotNull(cart);
        assertEquals(HttpStatus.OK, cartEx.getStatusCode());
        assertTrue(cart.isEmpty());

        ResponseEntity<Item> itemEx = templateWithAdmin().exchange("/cart", HttpMethod.POST, entity(3, "admin"), ITEM);//TODO
        Item item = itemEx.getBody();
        assert item != null;
        assertEquals(3, item.getId());
        cartEx = templateWithAdmin().exchange("/cart", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEM_COUNT);//TODO
        cart = cartEx.getBody();
        assertNotNull(cart);
        assertEquals(1, cart.size());
        templateWithAdmin().exchange("/cart/3", HttpMethod.DELETE, entity(3, "admin"), LIST_OF_ITEM_COUNT);//TODO
        cartEx = templateWithAdmin().exchange("/cart", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEM_COUNT);//TODO
        cart = cartEx.getBody();
        assertNotNull(cart);
        assertTrue(cart.isEmpty());
    }

    @Test
    void cart_add_clear() {
        templateWithAdmin().exchange("/cart", HttpMethod.POST, entity(2, "admin"), ITEM);//TODO
        templateWithAdmin().exchange("/cart", HttpMethod.POST, entity(4, "admin"), ITEM);//TODO
        templateWithAdmin().exchange("/cart", HttpMethod.POST, entity(2, "admin"), ITEM);//TODO
        templateWithAdmin().exchange("/cart", HttpMethod.POST, entity(5, "admin"), ITEM);//TODO
        ResponseEntity<List<ItemCount>> cartEx = templateWithAdmin().exchange("/cart", HttpMethod.GET, entity(null, "admin"), LIST_OF_ITEM_COUNT);//TODO
        List<ItemCount> cart = cartEx.getBody();
        assertNotNull(cart);
        assertEquals(3, cart.size());
        cartEx = templateWithAdmin().exchange("/cart/shopping-cart", HttpMethod.DELETE, entity(null, "admin"), LIST_OF_ITEM_COUNT);//TODO
        cart = cartEx.getBody();
        assertNotNull(cart);
        assertTrue(cart.isEmpty());
    }
}
