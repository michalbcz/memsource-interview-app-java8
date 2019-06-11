package cz.bernhard.memsource.projects;

import cz.bernhard.memsource.configuration.Configuration;
import cz.bernhard.memsource.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectRepository {

    public static final String MEMSOURCE_PROJECT_LIST_API_ENDPOINT = "https://cloud.memsource.com/web/api2/v1/projects";
    public static final String MEMSOURCE_AUTH_API_ENDPOINT = "https://cloud.memsource.com/web/api2/v1/auth/login";

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private RestTemplate restTemplate;

    /** once we have token for authentication we use the same */
    private String token;

    public Projects getAll() {
        Projects projects = restTemplate.getForObject(MEMSOURCE_PROJECT_LIST_API_ENDPOINT + "?token=" + getAuthToken(), Projects.class);
        return projects;
    }


    private String getAuthToken() {
        if (token == null) {
            token = getAuthTokenFromApi();
        }

        return token;
    }
    /**
     * @return authentication token
     */
    private String getAuthTokenFromApi() {
        Configuration configuration = configurationService.getConfiguration();

        Map<String, String> auth = new HashMap<>();
        auth.put("userName", configuration.getUsername());
        auth.put("password", configuration.getPassword());

        MemsourceAuthApiResponse response = restTemplate.postForObject(MEMSOURCE_AUTH_API_ENDPOINT, auth, MemsourceAuthApiResponse.class);
        return response.getToken();
    }

}
