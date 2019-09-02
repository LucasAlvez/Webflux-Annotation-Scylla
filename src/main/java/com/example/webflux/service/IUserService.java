package com.example.webflux.service;

import com.example.webflux.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * The interface User service.
 */
public interface IUserService {

    /**
     * Create mono.
     *
     * @param user the user
     * @return the mono
     */
    Mono<User> create(User user);

    /**
     * Update mono.
     *
     * @param user the user
     * @return the mono
     */
    Mono<User> update(User user);

    /**
     * Find by id mono.
     *
     * @param cpf the cpf
     * @return the mono
     */
    Mono<User> findById(String cpf);

    /**
     * Find all flux.
     *
     * @return the flux
     */
    Flux<User> findAll();

    /**
     * Delete mono.
     *
     * @param cpf the cpf
     * @return the mono
     */
    Mono<Void> delete(String cpf);

    /**
     * Stream flux.
     *
     * @return the flux
     */
    Flux<Tuple2<Long, User>> stream();
}
