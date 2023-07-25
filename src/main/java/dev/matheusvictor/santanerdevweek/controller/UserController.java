package dev.matheusvictor.santanerdevweek.controller;

import dev.matheusvictor.santanerdevweek.domain.model.User;
import dev.matheusvictor.santanerdevweek.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping
  public ResponseEntity<User> save(@RequestBody @Valid User userToCreate) {
    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
      .buildAndExpand(userToCreate.getId()).toUri();
    return ResponseEntity.created(location).body(userService.save(userToCreate));
  }
}
