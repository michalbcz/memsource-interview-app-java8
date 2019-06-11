package cz.bernhard.memsource.configuration;

import org.mapdb.DB;
import org.mapdb.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentMap;

@Repository
public class ConfigurationRepository {

    public static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    private ConcurrentMap<String, String> configurationDb;

    @Autowired
    private DB mapDb;

    @PostConstruct
    public void initialize() {
        configurationDb = mapDb.hashMap("configuration", Serializer.STRING, Serializer.STRING).createOrOpen();
    }

    public Configuration get() {
        String username = configurationDb.get(USERNAME_KEY);
        String password = configurationDb.get(PASSWORD_KEY);

        return new Configuration(username, password);
    }

    /**
     * @param newConfiguration to persist
     * @return actually saved configuration object
     */
    public Configuration save(Configuration newConfiguration) {
        configurationDb.put(USERNAME_KEY, newConfiguration.getUsername());
        configurationDb.put(PASSWORD_KEY, newConfiguration.getPassword());

        // flush data to file db
        mapDb.commit();

        return get();
    }

}
