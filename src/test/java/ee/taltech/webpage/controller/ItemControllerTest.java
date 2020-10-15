package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    public static final ParameterizedTypeReference<List<Item>> LIST_OF_ITEMS = new ParameterizedTypeReference<>() {};
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

}