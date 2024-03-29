package com.githooknotifyline.gitlabxlineapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RepositoryDto {
    private String name;
    private String url;
    private String description;
    private String homepage;
    @JsonProperty("git_http_url")
    private String gitHttpUrl;
    @JsonProperty("git_ssh_url")
    private String gitSshUrl;
    @JsonProperty("visibility_level")
    private Integer visibilityLevel;
}
