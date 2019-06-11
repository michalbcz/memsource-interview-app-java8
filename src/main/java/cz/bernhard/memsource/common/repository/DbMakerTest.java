package cz.bernhard.memsource.common.repository;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DbMakerTest {

    @Bean
    public DB db() {
        return DBMaker.tempFileDB().closeOnJvmShutdown().fileDeleteAfterClose().make();
    }


}
