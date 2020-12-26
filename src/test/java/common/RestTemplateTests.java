package common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RestTemplateTests {

    @Autowired
    private TestRestTemplate testRestTemplate;


    public TestRestTemplate template() {
        return testRestTemplate;
    }

    public TestRestTemplate templateWithUser() {
        return testRestTemplate.withBasicAuth("user", "user");
    }

    public TestRestTemplate templateWithAdmin() {
        return testRestTemplate.withBasicAuth("admin", "admin");
    }
}
