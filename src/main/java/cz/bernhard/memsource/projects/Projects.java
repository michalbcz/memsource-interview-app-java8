package cz.bernhard.memsource.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Projects {

    private List<Project> projects;

    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonProperty("content")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
