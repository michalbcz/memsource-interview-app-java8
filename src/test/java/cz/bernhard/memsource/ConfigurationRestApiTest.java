package cz.bernhard.memsource;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.bernhard.memsource.configuration.Configuration;
import cz.bernhard.memsource.configuration.ConfigurationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ConfigurationRestApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConfigurationRepository mockRepository;

    @Autowired
    private ObjectMapper jsonMapper;

    @Test
    public void get_should_return_configuration() throws Exception {
        // given
        Configuration mockConfiguration = new Configuration("username", "password");
        given(mockRepository.get()).willReturn(mockConfiguration);

        // when
        ResultActions result = mvc.perform(get("/api/configuration"));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("username")))
                .andExpect(jsonPath("$.password", is("password")))
        ;
    }

    @Test
    public void post_should_save_configuration() throws Exception {
        // given
        Configuration newConfiguration = new Configuration("newusername", "newpassword");
        String configurationAsJson = jsonMapper.writeValueAsString(newConfiguration);

        // when
        ResultActions result = mvc.perform(post("/api/configuration").content(configurationAsJson).contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk());
        verify(mockRepository).save(newConfiguration);
    }

    @Test
    public void saving_empty_username_should_fail() throws Exception {
        // given
        Configuration newConfiguration = new Configuration("", "newpassword");
        String configurationAsJson = jsonMapper.writeValueAsString(newConfiguration);

        // when
        ResultActions result = mvc.perform(post("/api/configuration").content(configurationAsJson).contentType(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].message", is("Username is mandatory")))
                .andExpect(jsonPath("$.errors[0].fieldName", is("username")))
        ;

        verifyZeroInteractions(mockRepository );
    }

    @Test
    public void saving_empty_password_should_fail() throws Exception {
        // given
        Configuration newConfiguration = new Configuration("_", "");
        String configurationAsJson = jsonMapper.writeValueAsString(newConfiguration);

        // when
        ResultActions result = mvc.perform(post("/api/configuration").content(configurationAsJson).contentType(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].message", is("Password is mandatory")))
                .andExpect(jsonPath("$.errors[0].fieldName", is("password")))
        ;

        verifyZeroInteractions(mockRepository );
    }

}
