package cz.bernhard.memsource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    public ConfigurationRepository configurationRepository;

    public Configuration getConfiguration() {
        return configurationRepository.get();
    }

    public Configuration save(Configuration configuration) {
        return configurationRepository.save(configuration);
    }
}
