package com.apiteria.githubrepo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepository {

    private String name;
    private Owner  owner;
    private List<Branch> branches;


}
