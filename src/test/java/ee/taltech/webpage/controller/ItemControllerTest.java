
package ee.taltech.webpage.controller;

import ee.taltech.webpage.common.RestTemplateTests;
import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ItemControllerTest extends RestTemplateTests {
    JwtTokenProvider jwtTokenProvider;

    @Test
    void one_can_query_items() {
        ResponseEntity<List<Item>> exchange = templateWithUser().exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        assertNotNull(items);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertFalse(items.isEmpty());
        Item item = items.get(0);
        assertEquals("Odens", item.getName());
    }

    @Test
    void test_strength() {
        ResponseEntity<List<Item>> exchange = template().exchange("/items/?filter=strength&direction=MIN", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);
        assertEquals("KNOX", item.getName());
        assertEquals(14, items.size());
        item = items.get(items.size() - 1);
        assertEquals("Odens", item.getName());
        exchange = template().exchange("/items/?filter=strength", HttpMethod.GET, null, LIST_OF_ITEMS);
        items = exchange.getBody();
        item = items.get(0);
        assertEquals("Odens", item.getName());
        assertEquals(14, items.size());
        item = items.get(items.size() - 1);
        assertEquals("KNOX", item.getName());
    }

    @Test
    void rating_most_popular() {
        ResponseEntity<List<Item>> exchange = templateWithUser().exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
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
        exchange = templateWithUser().exchange("/items/?filter=rating", HttpMethod.GET, null, LIST_OF_ITEMS);
        items = exchange.getBody();
        item0 = items.get(0);
        assertEquals(item0Id, item0.getId());
        Item item1 = items.get(1);
        assertEquals(2, item1.getId());
        Item item2 = items.get(2);
        assertEquals(3, item2.getId());
    }

    @Test
    void max_price() {
        ResponseEntity<List<Item>> exchange = template().exchange("/items/?filter=price&direction=MIN", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);
        assertEquals("Skruf", item.getName());
        assertEquals(14, items.size());
    }


    @Test
    void item_by_id() {
        ResponseEntity<Item> exchange = template().exchange("/items/3", HttpMethod.GET, null, ITEM);
        Item item = exchange.getBody();
        assert item != null;
        assertEquals(3, item.getId());
    }

    @Test
    void grading_correct() {
        ResponseEntity<List<Item>> exchange = templateWithUser().exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);

        //Long itemId = item.getId();
        assertEquals(0, item.getRating());
        //ResponseEntity<Double> addGradeToItem = template().exchange("/items/" + itemId + "/grade", HttpMethod.POST, null, ITEM_ID);
        templateWithAdmin().exchange("/items/1/rating", HttpMethod.POST, entity(5, "admin"), ITEM_ID);
        templateWithAdmin().exchange("/items/1/rating", HttpMethod.POST, entity(5, "admin"), ITEM_ID);
        templateWithAdmin().exchange("/items/1/rating", HttpMethod.POST, entity(4, "admin"), ITEM_ID);
        templateWithAdmin().exchange("/items/1/rating", HttpMethod.POST, entity(5, "admin"), ITEM_ID);
        templateWithAdmin().exchange("/items/1/rating", HttpMethod.POST, entity(2, "admin"), ITEM_ID);
        ResponseEntity<Item> itemEx = template().exchange("/items/1", HttpMethod.GET, null, ITEM);
        Item item2 = itemEx.getBody();
        assertEquals(4.2, item2.getRating());
        item2 = items.get(3);
        assertEquals(0, item2.getRating());
    }

    @Test
    void refactor_item() {
        ResponseEntity<List<Item>> exchange = templateWithAdmin().exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
        List<Item> items = exchange.getBody();
        Item item = items.get(0);
        item.changePrice(3.3);
        Double price = item.getPrice();
        Item newItem = new Item();
        ItemCount itemCount = new ItemCount();
        ItemCount itemCount7 = new ItemCount(item, 7);
        assertNull(itemCount.getId());
        assertNull(itemCount.getItem());
        assertNotEquals(itemCount.getQuantity(), itemCount7.getQuantity());
        itemCount.setId(343L);
        itemCount7.setId(343L);
        assertEquals(itemCount.getId(), itemCount7.getId());
        itemCount.setItem(item);
        assertEquals(itemCount.getItem(), itemCount7.getItem());
        itemCount.setQuantity(7);
        assertEquals(itemCount.getQuantity(), itemCount7.getQuantity());
        newItem.changeDescription("new taste of freedom");
        newItem.changePrice(3.3);
        assertEquals(price, newItem.getPrice());
        assertEquals("new taste of freedom", newItem.getDescription());
    }

    private <T> HttpEntity<T> entity(T param, String name) {

        HttpHeaders headers = authorizationHeader(name);
        return new HttpEntity<>(param, headers);
    }

}

