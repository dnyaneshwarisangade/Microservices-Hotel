package com.user.service;

import com.user.service.entities.Rating;
import com.user.service.external.services.RatingServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
    @Autowired
	private RatingServiceClient ratingServiceClient;
/*
	@Test
	void create(){
		Rating rating= Rating.builder().rating(10).userId("").hotelId("").feedback("Testing Rating Service").build();
		Rating savedrating = ratingServiceClient.createRating(rating);
		System.out.println("New rating created!!");
	}*/


}
