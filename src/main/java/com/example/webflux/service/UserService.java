package com.example.webflux.service;

import com.example.webflux.domain.User;
import com.example.webflux.domain.UserKey;
import com.example.webflux.exception.DataNotFoundException;
import com.example.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type User service.
 */
@Service
public class UserService implements IUserService {

    /**
     * User repository.
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Mono<User> update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Mono<User> findById(String cpf) {
        return this.userRepository.findById(new UserKey(cpf))
                .switchIfEmpty(Mono.error(
                        new DataNotFoundException("User not Found")));
    }

    @Override
    public Flux<User> findAll() {
        return this.userRepository.findAll()
                .switchIfEmpty(Mono.error(
                        new DataNotFoundException("The database is empty")));
    }

    @Override
    public Mono<Void> delete(String cpf) {
        return this.userRepository.deleteById(new UserKey(cpf));
    }
}
