package com.rating.services;

import com.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
     Rating create(Rating rating);
     List<Rating> getRatings();
     List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
