package cz.bernhard.memsource.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.bernhard.memsource.MemsourceInterviewApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MemsourceInterviewApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ConfigurationRestApiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper jsonMapper;


    @Test
    public void post_should_save_configuration() throws Exception {
        // given
        Configuration newConfiguration = new Configuration("newusername123", "newpassword123");
        String configurationAsJson = jsonMapper.writeValueAsString(newConfiguration);

        // when
        ResultActions result = mvc.perform(post("/api/configuration")
                .content(configurationAsJson)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk());

        // when
        result = mvc.perform(get("/api/configuration"));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("newusername123")))
                .andExpect(jsonPath("$.password", is("newpassword123")))
        ;


    }

}
