package com.infotravel.teste.service;

import com.infotravel.teste.model.Authentication;
import com.infotravel.teste.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AuthenticationService {
    @Autowired
    RestTemplate template = new RestTemplate();

    @PostMapping
    public Authentication getAuthentication() {
        User user = new User();
        HttpEntity<User> request = new HttpEntity<>(user, new HttpHeaders());

        System.out.println(user.username);

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.infotravel.com.br")
                .path("api/v1/authenticate")
                .queryParam("username", user.username)
                .queryParam("password", user.password)
                .queryParam("client", user.client)
                .queryParam("agency", user.agency)
                .build();

        return template.postForObject(uri.toUriString(), request, Authentication.class);
    }
}
