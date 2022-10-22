package com.infotravel.teste.controller;


import com.infotravel.teste.model.Authentication;
import com.infotravel.teste.model.FindHotel;
import com.infotravel.teste.model.HotelDetail;
import com.infotravel.teste.service.AuthenticationService;
import com.infotravel.teste.service.FindService;
import com.infotravel.teste.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/infotravel")
public class HotelController {

    @Autowired
    private FindService findService;

    @Autowired HotelService hotelService;

    @Autowired
    private AuthenticationService auth;

    @GetMapping("/search/{destination}/{start}/{end}/{occupancy}")
    public FindHotel getHotelAvail(@PathVariable String destination,
                                   @PathVariable String start,
                                   @PathVariable String end,
                                   @PathVariable String occupancy) {
        return findService.getHoteis(destination, start, end, occupancy, auth.getAuthentication());
    }


    @GetMapping("/hotel")
    public HotelDetail getHotelDetail(@RequestParam String keyDetail) {
        return hotelService.getHotel(keyDetail, auth.getAuthentication());
    }

}
