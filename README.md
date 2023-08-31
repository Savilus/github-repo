# Github repo

### Introduce 
I present you a simple spring application that uses Github API. The main task of the application is to search for the user we specify and list all of his unforked repositories. All the branches of those repositories and the last SHA comit from each of the branches.

The condition to find the user is to send a request with the header "Accept: application/json" otherwise a 406 error will be returned.

Additionally, if the user does not exist, a 404 error will be returned with the message "User not found on GitHub."

### Running the application
***
**1.** Clone the source code from Github:
````
https://github.com/Savilus/github-repo.git
````
**2.** Run the API from  *GithubRepoApplication.class*
***
To create this application I used:

<div align="center">
<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java" /></code>
<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring" /></code>
<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot" /></code>
<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven" /></code>
<code><img height="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok" /></code>
</div>
