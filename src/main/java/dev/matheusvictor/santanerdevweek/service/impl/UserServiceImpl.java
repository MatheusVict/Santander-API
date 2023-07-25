package dev.matheusvictor.santanerdevweek.service.impl;

import dev.matheusvictor.santanerdevweek.domain.model.User;
import dev.matheusvictor.santanerdevweek.domain.respositoy.UserRepository;
import dev.matheusvictor.santanerdevweek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public User save(User userToCreate) {
    if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
      throw new IllegalArgumentException("Account number already exists");
    }

    return userRepository.save(userToCreate);
  }
}
