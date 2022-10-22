package com.infotravel.teste.service;

import com.infotravel.teste.model.Authentication;
import com.infotravel.teste.model.FindHotel;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class FindService {

    //Criação do template para requisição rest
    public static RestTemplate template = new RestTemplate();

    //Metodo para encontrar hoteis
    @GetMapping
    public static FindHotel getHoteis(String destination,
                               String start,
                               String end,
                               String occupancy,
                               Authentication aut) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", aut.getType() + " " + aut.getAccessToken());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<?> request = new HttpEntity<>(headers);

        UriComponents uri = UriComponentsBuilder.newInstance().scheme("http").host("api.infotravel.com.br")
                .path("api/v1/avail/hotel").queryParam("destination", destination).queryParam("start", start)
                .queryParam("end", end).queryParam("occupancy", occupancy).build();
        ResponseEntity<FindHotel> response = template.exchange(uri.toUriString(),
                HttpMethod.GET, request, FindHotel.class);

        return response.getBody();
    }
}
