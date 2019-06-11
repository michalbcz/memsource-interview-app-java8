package cz.bernhard.memsource.common.repository;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.File;

@Configuration
@Profile("production")
public class DbMaker {

    @Value("${memsource.db.filename}")
    private String fileName;

    @Bean
    public DB db() {
        String dbFilePath = System.getProperty("user.home") + File.separator + fileName;
        return DBMaker.fileDB(dbFilePath).closeOnJvmShutdown().make();
    }


}
