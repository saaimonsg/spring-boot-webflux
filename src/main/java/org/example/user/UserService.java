package org.example.user;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<User> findById(Long id) {
        return repository.findById(id)
                .filter(User::isActive);
    }

    public Mono<User> create(User user) {
        return repository.save(user);
    }
}
