package cz.bernhard.memsource.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    private String sourceLanguage;

    private List<String> targetLanguages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("sourceLanguage")
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    @JsonProperty("sourceLang")
    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    @JsonProperty("targetLanguages")
    public List<String> getTargetLanguages() {
        return targetLanguages;
    }

    @JsonProperty("targetLangs")
    public void setTargetLanguages(List<String> targetLanguages) {
        this.targetLanguages = targetLanguages;
    }
}
