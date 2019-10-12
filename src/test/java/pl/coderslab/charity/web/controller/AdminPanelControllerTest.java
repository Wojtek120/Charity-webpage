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
public class AdminPanelControllerTest {

    @Autowired
    private WebApplicationContext context;
    private final String ADMIN_PANEL_PAGE_NAME = "adminPanel";
    private final String ADMIN_INSTITUTIONS_PAGE_NAME = "adminInstitutions";
    private final String EDIT_INSTITUTIONS_PAGE_NAME = "editInstitution";
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void adminPanel() throws Exception {
        mockMvc.perform(get("/admin/panel"))
                .andExpect(status().isOk())
                .andExpect(view().name(ADMIN_PANEL_PAGE_NAME));
    }

    @Test
    public void prepareInstitutions() throws Exception {
        mockMvc.perform(get("/admin/institutions"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("institutions"))
                .andExpect(view().name(ADMIN_INSTITUTIONS_PAGE_NAME));
    }

    @Test
    public void prepareInstitutionToEdit() throws Exception {
        mockMvc.perform(get("/admin/institutions/edit/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("institution"))
                .andExpect(view().name(EDIT_INSTITUTIONS_PAGE_NAME));
    }



    @Test
    public void registerNewUser() throws Exception {
        mockMvc.perform(post("/admin/institutions/edit/{id}",1L)
                .param("name", RandomStringUtils.randomAlphabetic(8))
                .param("description", RandomStringUtils.randomAlphabetic(8)))
                .andExpect(redirectedUrl("/admin/institutions"));
    }

}