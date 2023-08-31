package com.apiteria.githubrepo.controllers;

import com.apiteria.githubrepo.entities.GitHubRepository;
import com.apiteria.githubrepo.services.ErrorHandlingService;
import com.apiteria.githubrepo.services.GithubApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class GithubApiController {

    private final GithubApiService githubApiService;
    private final ErrorHandlingService errorHandlingService;

    @GetMapping(value = "/repositories/{username}")
    public ResponseEntity<?> getRepositories(@PathVariable String username,
                                             @RequestHeader(name = "Accept") String acceptHeader) {

        List<GitHubRepository> repositories = new ArrayList<>();
        if (acceptHeader.equals("application/json")) {
            try {
                repositories = githubApiService.getRepositories(username);
                if (repositories.isEmpty()) {
                    return errorHandlingService.createUserNotFoundError(HttpStatus.NOT_FOUND, "User not found on GitHub.");
                }
            } catch (JsonProcessingException jsonException) {
                log.info("An error has occurred during serialization: " + jsonException.getMessage());
            } catch (Exception exception) {
                log.info("An unexpected error occurred: " + exception.getMessage());
                return errorHandlingService.createUnexpectedError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            }
        } else {
            return errorHandlingService.wrongHeaderError(HttpStatus.NOT_ACCEPTABLE, "Only application/json header is acceptable");
        }

        return ResponseEntity.ok().body(repositories);

    }
}