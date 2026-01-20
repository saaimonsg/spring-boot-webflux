package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hola WebFlux 👋");
    }

    @GetMapping("/json")
    public Mono<User> user() {
        return Mono.just(new User("admin", "admin"));
    }
    @GetMapping("/delayed")
    public Mono<String> delayed() {
        return Mono
                .just("Respuesta después de 3 segundos")
                .delayElement(java.time.Duration.ofSeconds(3));
    }
    @GetMapping("/thread")
    public Mono<String> thread() {
        return Mono.fromSupplier(() ->
                "Hilo actual: " + Thread.currentThread().getName()
        );
    }
    @GetMapping("/blocking")
    public Mono<String> blocking() {
        return Mono.fromCallable(() -> {
                    Thread.sleep(2000); // simula JDBC
                    return "Trabajo bloqueante";
                })
                .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic());
    }

}