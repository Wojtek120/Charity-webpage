package pl.coderslab.charity.web.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
public class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;
    private final String REGISTRATION_PAGE_NAME = "registration";
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void prepareRegistration() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(model().attributeExists("newUser"))
                .andExpect(model().attribute("newUser", hasProperty("id", isEmptyOrNullString())))
                .andExpect(model().attribute("newUser", hasProperty("email", isEmptyOrNullString())))
                .andExpect(model().attribute("newUser", hasProperty("password", isEmptyOrNullString())))
                .andExpect(model().attribute("newUser", hasProperty("repeatedPassword", isEmptyOrNullString())))
                .andExpect(model().attribute("newUser", hasProperty("firstName", isEmptyOrNullString())))
                .andExpect(model().attribute("newUser", hasProperty("lastName", isEmptyOrNullString())))
                .andExpect(model().attribute("newUser", hasProperty("phoneNumber", isEmptyOrNullString())))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION_PAGE_NAME));
    }

    @Test
    public void registerNewUser() throws Exception {
        mockMvc.perform(post("/registration").param("email", "user@user.com")
        .param("password", RandomStringUtils.randomAlphabetic(8))
        .param("firstName", RandomStringUtils.randomAlphabetic(8))
        .param("lastName", RandomStringUtils.randomAlphabetic(8)))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void registerNewUser_withWrongEmail() throws Exception {
        mockMvc.perform(post("/registration").param("email", "e")
        .param("password", RandomStringUtils.randomAlphabetic(8))
        .param("firstName", RandomStringUtils.randomAlphabetic(8))
        .param("lastName", RandomStringUtils.randomAlphabetic(8)))
                .andExpect(view().name(REGISTRATION_PAGE_NAME));
    }
}