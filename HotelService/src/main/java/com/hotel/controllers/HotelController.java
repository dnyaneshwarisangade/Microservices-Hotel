package com.hotel.controllers;

import com.hotel.entities.Hotel;
import com.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @PostMapping
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel) {
        Hotel hotel1 =hotelService.create(hotel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllUsers() {
        List<Hotel> hotel1 = hotelService.getAll();
        return ResponseEntity.ok(hotel1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getUserById(@PathVariable String id) {
        Hotel hotel1 = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel1);
    }
}
