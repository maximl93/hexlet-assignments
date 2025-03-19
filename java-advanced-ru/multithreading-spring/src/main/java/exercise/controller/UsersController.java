package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("")
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/{id}")
    public Mono<User> update(@PathVariable Long id,
                             @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }
    // END
}
