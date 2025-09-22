package com.user.service.external.services;

import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Service
@FeignClient(name="RATINGSERVICE")
public interface RatingServiceClient {

    @PostMapping("/ratings")
    Rating createRating(Rating values);

    @GetMapping("/ratings")
    List<Rating> getAllRatings();

    @GetMapping("/ratings/{userId}")
    List<Rating> getAllRatingsByUserId(@PathVariable String userId);

    @GetMapping("/ratings/{hotelId}")
    List<Rating> getAllRatingsByHotelId(@PathVariable String hotelId);

}
