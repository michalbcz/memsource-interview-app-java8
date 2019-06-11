package cz.bernhard.memsource.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapdb.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) /* we need to have empty db each test method */
public class ConfigurationRepositoryTest {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private DB db;

    @Test
    public void first_get_should_return_empty_db() {
        // when
        Configuration configuration = configurationRepository.get();

        // then
        assertThat(configuration.getUsername()).isBlank();
        assertThat(configuration.getPassword()).isBlank();
    }

    @Test
    public void save_is_successfull() {
        // given
        Configuration newConfiguration = new Configuration("username", "password");

        // when
        Configuration savedConfiguration = configurationRepository.save(newConfiguration);

        // then
        assertThat(savedConfiguration.getUsername()).isEqualTo("username");
        assertThat(savedConfiguration.getPassword()).isEqualTo("password");

        // when
        Configuration getConfiguration = configurationRepository.get();

        // then
        assertThat(savedConfiguration.getUsername()).isEqualTo("username");
        assertThat(savedConfiguration.getPassword()).isEqualTo("password");
    }


}
