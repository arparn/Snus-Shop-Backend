package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    public static final ParameterizedTypeReference<List<Item>> LIST_OF_ITEMS = new ParameterizedTypeReference<>() {};
    public static final ParameterizedTypeReference<Item> ITEM = new ParameterizedTypeReference<>() {};
    public static final ParameterizedTypeReference<List<ItemCount>> LIST_OF_ITEM_COUNT = new ParameterizedTypeReference<>() {};
    public static final ParameterizedTypeReference<Double> ITEM_ID = new ParameterizedTypeReference<>() {};
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void one_can_query_items(){
        ResponseEntity<List<Item>> exchange = testRestTemplate.exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        assertNotNull(items);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertFalse(items.isEmpty());
        Item item = items.get(0);
        assertEquals("Odens", item.getName());
    }

    @Test
    void test_min_strength(){
        ResponseEntity<List<Item>> exchange = testRestTemplate.exchange("/items?filter=strength&direction=MIN", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);
        assertEquals("KNOX", item.getName());
        assertEquals(14 ,items.size());
        item = items.get(items.size() - 1);
        assertEquals("Odens", item.getName());
    }

    @Test
    void grading_correct(){
        ResponseEntity<List<Item>> exchange = testRestTemplate.exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);
        //Long itemId = item.getId();
        assertEquals(0, item.getRating());
        //ResponseEntity<Double> addGradeToItem = testRestTemplate.exchange("/items/" + itemId + "/grade", HttpMethod.POST, null, ITEM_ID);
        item.addGrade(5);
        assertEquals(5, item.getRating());
        item.addGrade(5);
        item.addGrade(4);
        item.addGrade(5);
        item.addGrade(2);
        assertEquals(4.2, item.getRating());
        item = items.get(3);
        assertEquals(0, item.getRating());
    }

    @Test
    void comment_correct(){
        Comment comment1 = new Comment("Ilja", "Boit", "My comment1");
        Comment comment2 = new Comment("Ilja", "Boits", "My comment2");
        Comment comment3 = new Comment("Ilja", "Boitss", "My comment3");
        Comment comment4 = new Comment("Ilja", "Boitsss", "My comment4");
        ResponseEntity<List<Item>> exchange = testRestTemplate.exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);
        assertEquals(0, item.getComments().size());
        item.addComment(comment1);
        assertEquals(1, item.getComments().size());
        item.addComment(comment2);
        item.addComment(comment3);
        item.addComment(comment4);
        assertEquals("My comment4", item.getComments().get(3).getComment());
    }

    @Test
    void item_by_id(){
        ResponseEntity<Item> exchange = testRestTemplate.exchange("/items/3", HttpMethod.GET, null, ITEM);
        Item item = exchange.getBody();
        assert item != null;
        assertEquals(3, item.getId());
    }

    @Test
    void rating_most_popular(){
        ResponseEntity<List<Item>> exchange = testRestTemplate.exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item0 = items.get(0);
        item0.addGrade(4);
        Long item0Id = item0.getId();
        Item item3 = items.get(3);
        item0.addGrade(4);
        item3.addGrade(5);
        item3.addGrade(5);
        Item item6 = items.get(6);
        item6.addGrade(4);
        item6.addGrade(4);
        item6.addGrade(5);
        exchange = testRestTemplate.exchange("/items?filter=rating", HttpMethod.GET, null, LIST_OF_ITEMS);
        items = exchange.getBody();
        item0 = items.get(0);
        assertEquals(item0Id, item0.getId());
        Item item1 = items.get(1);
        assertEquals(3, item1.getId());
        Item item2 = items.get(2);
        assertEquals(4, item2.getId());
    }

    @Test
    void empty_cart_and_wishlist(){
        ResponseEntity<List<ItemCount>> exchangeItemCount = testRestTemplate.exchange("/cart", HttpMethod.GET, null, LIST_OF_ITEM_COUNT);
        ResponseEntity<List<Item>> exchangeWishlist = testRestTemplate.exchange("/wishlist", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchangeWishlist.getBody();
        assertNotNull(items);
        assertEquals(HttpStatus.OK, exchangeWishlist.getStatusCode());
        assertTrue(items.isEmpty());
        Item item0 = new Item();
        ItemCount itemCount0 = new ItemCount();
        ResponseEntity<Item> addItem = testRestTemplate.exchange("/wishList", HttpMethod.POST, null, ITEM);
        List<ItemCount> itemCounts = exchangeItemCount.getBody();
        assertNotNull(itemCounts);
        assertEquals(HttpStatus.OK, exchangeItemCount.getStatusCode());
        assertTrue(itemCounts.isEmpty());
    }
}