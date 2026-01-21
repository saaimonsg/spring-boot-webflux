package org.example.user;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Mono<User> create(@RequestBody Mono<User> userMono) {
        return userMono.flatMap(service::create);
    }
}
