package com.hotel.services;

import com.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel> getAll ();

    Hotel getHotelById(String Id);
}
