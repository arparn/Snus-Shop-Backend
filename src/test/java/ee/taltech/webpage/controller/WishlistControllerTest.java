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
    void wishlist() {

    }

    private <T> HttpEntity<T> entity(T param, String name) {
        HttpHeaders headers = authorizationHeader(name);
        return new HttpEntity<>(param, headers);
    }
}
