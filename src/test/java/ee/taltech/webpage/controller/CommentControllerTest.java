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

public class CommentControllerTest extends RestTemplateTests {

    @Test
    void comment_correct() {
        Comment comment1 = new Comment("Ilja", "Boit", "My comment1");
        Comment comment2 = new Comment("Ilja", "Boits", "My comment2");
        Comment comment3 = new Comment("Ilja", "Boitss", "My comment3");
        Comment comment4 = new Comment("Ilja", "Boitsss", "My comment4");
        ResponseEntity<List<Item>> exchange = templateWithUser().exchange("/items", HttpMethod.GET, null, LIST_OF_ITEMS);
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
}
