package cz.bernhard.memsource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationRestController {

    @Autowired
    public ConfigurationService configurationService;

    @GetMapping
    public Configuration get() {
        return configurationService.getConfiguration();
    }

    @PostMapping
    public Configuration post(@Valid @RequestBody Configuration configuration) {
        return configurationService.save(configuration);
    }



}
