package com.user.service.services.impl;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.services.HotelServiceClient;
import com.user.service.repositories.UserRepositories;
import com.user.service.services.UserService;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private Logger log= org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

   @Autowired
   private UserRepositories userRepositories;

   @Autowired
   private HotelServiceClient hotelServiceClient;

   @Autowired
   private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepositories.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user= userRepositories.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server!!: "+userId));
        //fetch user from ratings
      Rating[] ratings=restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        log.info("{} ",ratings);
        List<Rating> ratingsOfUser= Arrays.stream(ratings).toList();

        List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
                System.out.println(rating.getHotelId());
                //api call to hotelService to get hotel
              /* ResponseEntity<Hotel> forEntity =restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();log.info("response status code: {}",forEntity.getStatusCode()); */

            Hotel hotel=hotelServiceClient.getHotel(rating.getHotelId());
                //set the hotel to rating
            rating.setHotel(hotel);
                //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRating(ratingList);

        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(String userId) {
        return null;
    }
}
