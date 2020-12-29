package ee.taltech.webpage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.taltech.webpage.service.users.dto.LoginDto;
import ee.taltech.webpage.service.users.dto.RegisterDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class IndexMockMvcControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    //    @DisplayName("name of the test")
    public void register() throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("user");
        registerDto.setPassword("user");
        mvc.perform(MockMvcRequestBuilders.post("/user/reg").contentType(APPLICATION_JSON).content(asJsonString(registerDto)).accept(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void login() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("user");
        loginDto.setPassword("user");
        mvc.perform(MockMvcRequestBuilders.post("/user/log").contentType(APPLICATION_JSON).content(asJsonString(loginDto)).accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
