package com.example.webflux.controller;

import com.example.webflux.domain.User;
import com.example.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
public class UserController {

    /**
     * User service.
     */
    @Autowired
    private UserService userService;

    /**
     * Create user.
     * @param user
     * @return Mono<User>
     */
    @PostMapping
    public Mono<User> create(@RequestBody User user){
        return this.userService.create(user);
    }

    /**
     * Update user.
     * @param user
     * @return Mono<User>
     */
    @PutMapping
    public Mono<User> update(@RequestBody User user){
        return this.userService.create(user);
    }

    /**
     * Find user by id.
     * @param cpf
     * @return Mono<User>
     */
    @GetMapping(value="/{cpf}")
    public Mono<User> findById(@PathVariable("cpf") String cpf){
        return this.userService.findById(cpf);
    }

    /**
     * Find all users.
     * @return Flux<User>
     */
    @GetMapping
    public Flux<User> findAll(){
        return userService.findAll();
    }

    /**
     * Delete user.
     * @param cpf
     * @return Mono<Void>
     */
    @DeleteMapping(value="/{cpf}")
    public Mono<Void> delete(@PathVariable("cpf") String cpf){
        return this.userService.delete(cpf);
    }

    /**
     * Event stream example.
     * @return Flux<Tuple2<Long, User>>
     */
    @GetMapping(value="/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, User>> stream(){
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<User> events = userService.findAll();
        return Flux.zip(interval, events);
    }
}
