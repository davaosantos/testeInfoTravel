package com.infotravel.teste.service;

import java.util.Collections;

import com.infotravel.teste.model.Authentication;
import com.infotravel.teste.model.HotelDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public  class HotelService {

    @Autowired
    private RestTemplate template;

    @GetMapping
    public HotelDetail getHotel(String keyDetail,	Authentication aut) {

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", aut.getType() + " " + aut.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> request = new HttpEntity<>(headers);

        UriComponents uri = UriComponentsBuilder.newInstance().scheme("http").host("api.infotravel.com.br")
                .path("api/v1/utility/hotelDetail/"+keyDetail).build();

        ResponseEntity<HotelDetail> response = template.exchange(uri.toUriString(),
                HttpMethod.GET, request, HotelDetail.class);

        return response.getBody();
    }
}
