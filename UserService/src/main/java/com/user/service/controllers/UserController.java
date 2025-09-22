package com.user.service.controllers;

import com.user.service.entities.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user1 = userService.getAllUser();
        return ResponseEntity.ok(user1);
    }
    int retryCount=1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        log.info("Get Single User handler: UserController");
        log.info("Retry count : {}",retryCount);
        retryCount++;
        User user1 = userService.getUser(userId);
        return ResponseEntity.ok(user1);
    }

    //creating a fallback method for circuitBreaker
    public ResponseEntity<User> ratingHotelFallback( String userId,Exception ex) {
        log.info("Fallback is executed because rating is down : "+ex.getMessage());
        User user=User.builder().email("dummy@gmail.com").name("dummy").about("This dummy user is created because some service is down").userId("123456").build();
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }
}