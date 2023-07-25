package dev.matheusvictor.santanerdevweek.service;

import dev.matheusvictor.santanerdevweek.domain.model.User;

public interface UserService {
  User findById(Long id);
  User save(User userToCreate);
}
