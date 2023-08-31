package com.apiteria.githubrepo.services;

import com.apiteria.githubrepo.entities.Branch;
import com.apiteria.githubrepo.entities.GitHubRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GithubApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final String TOKEN = System.getenv("GITHUB_TOKEN");

    public List<GitHubRepository> getRepositories(String username) throws JsonProcessingException {
        String userUrl = "https://api.github.com/users/" + username + "/repos";
        List<GitHubRepository> ownerRepositories = new ArrayList<>();

//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            request.getHeaders().set("Authorization", "Bearer " + TOKEN);
//            return execution.execute(request, body);
//        });


        try {
            ResponseEntity<String> response = restTemplate.getForEntity(userUrl, String.class);

            String responseBody = response.getBody();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            for (JsonNode node : jsonNode) {
                boolean isFork = node.get("fork").asBoolean();

                if (!isFork) {
                    GitHubRepository ownerRepository = objectMapper.treeToValue(node, GitHubRepository.class);
                    if (ownerRepository != null) {
                        ownerRepository.setBranches(getBranches(ownerRepository.getName(), username, restTemplate));
                        ownerRepositories.add(ownerRepository);
                    }
                }
            }
        } catch (HttpClientErrorException.NotFound notFoundException) {
            log.info("Failed to find: " + username);
            return new ArrayList<>();
        }

        return ownerRepositories;

    }

    private List<Branch> getBranches(String repositoryName, String username, RestTemplate restTemplate) throws JsonProcessingException {
        String branchesUrl = "https://api.github.com/repos/" + username + "/" + repositoryName + "/branches";

        ResponseEntity<String> response = restTemplate.getForEntity(branchesUrl, String.class);
        List<Branch> branches = new ArrayList<>();

        String responseBody = response.getBody();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        for (JsonNode node : jsonNode) {
            Branch branch = objectMapper.treeToValue(node, Branch.class);
            branch.setLastCommitSha(node.get("commit").get("sha").asText());
            branches.add(branch);
        }

        return branches;
    }
}
