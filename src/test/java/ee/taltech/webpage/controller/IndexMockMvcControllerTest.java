/*
package ee.taltech.webpage.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class IndexMockMvcControllerTest {
    public static final String INDEX = "/";
    public static final String USER = "/user";
    public static final String ADMIN = "/admin";
    public static final String INDEX_CONTENT = "API is up";
    public static final String USER_CONTENT = "USER url";
    public static final String ADMIN_CONTENT = "ADMIN url";
    @Autowired
    private MockMvc mvc;

    @Test
    //    @DisplayName("name of the test")
    public void whenRequestIndex_thenReceiveGreeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(INDEX).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(INDEX_CONTENT)));
    }

    @Test
    public void user_url_for_guest_is_unauthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(USER).accept(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void user_url_is_for_user() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(USER).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(USER_CONTENT)));
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    public void user_url_is_for_admin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(USER).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(USER_CONTENT)));
    }

    @Test
    public void admin_url_is_unauthorized_for_guest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ADMIN).accept(APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "USER")
    public void admin_url_is_forbidden_for_user() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ADMIN).accept(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = {"USER", "ADMIN"})
    public void admin_url_is_for_admin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(ADMIN).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(ADMIN_CONTENT)));
    }
}
*/
