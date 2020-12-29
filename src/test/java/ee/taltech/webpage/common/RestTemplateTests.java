package ee.taltech.webpage.common;

import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.model.ItemCount;
import ee.taltech.webpage.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RestTemplateTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    public static final ParameterizedTypeReference<List<Item>> LIST_OF_ITEMS = new ParameterizedTypeReference<>() {
    };
    public static final ParameterizedTypeReference<Item> ITEM = new ParameterizedTypeReference<>() {
    };
    public static final ParameterizedTypeReference<List<ItemCount>> LIST_OF_ITEM_COUNT = new ParameterizedTypeReference<>() {
    };
    public static final ParameterizedTypeReference<Double> ITEM_ID = new ParameterizedTypeReference<>() {
    };

    public TestRestTemplate template() {
        return testRestTemplate;
    }

    public TestRestTemplate templateWithUser() {
        return testRestTemplate.withBasicAuth("user", "user");
    }

    public TestRestTemplate templateWithAdmin() {
        return testRestTemplate.withBasicAuth("admin", "admin");
    }

    public <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }

    protected HttpHeaders authorizationHeader(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtTokenProvider.createTokenForTests(username));
        return headers;
    }
}
