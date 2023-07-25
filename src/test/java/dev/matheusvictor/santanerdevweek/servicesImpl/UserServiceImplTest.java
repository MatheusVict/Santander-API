package dev.matheusvictor.santanerdevweek.servicesImpl;

import dev.matheusvictor.santanerdevweek.domain.model.Account;
import dev.matheusvictor.santanerdevweek.domain.model.User;
import dev.matheusvictor.santanerdevweek.domain.respositoy.UserRepository;
import dev.matheusvictor.santanerdevweek.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  public void testFindByIdReturnsUserWhenUserFound() {
    User expectedUser = new User();
    expectedUser.setId(1L);

    when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

    User ActualUser = userService.findById(1L);

    assertEquals(expectedUser, ActualUser);
  }

  @Test
  public void testSaveReturnsUserWhenAccountNumberDoesNotExist() {
    User userToSave = new User();
    Account account = new Account();
    account.setNumber("123456");
    userToSave.setAccount(account);

    when(userRepository.existsByAccountNumber("123456")).thenReturn(false);
    when(userRepository.save(userToSave)).thenReturn(userToSave);

    User savedUser = userService.save(userToSave);

    assertEquals(userToSave, savedUser);
  }

  @Test
  public void testSaveThrowsExceptionWhenAccountNumberExists() {
    User user = new User();
    Account account = new Account();
    account.setNumber("123456");
    user.setAccount(account);

    when(userRepository.existsByAccountNumber(anyString())).thenReturn(true);

    assertThrows(IllegalArgumentException.class, () -> {
      userService.save(user);
    });
  }

  @Test
  public void testFindByIdThrowsExceptionWhenUserNotFound() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> {
      userService.findById(1L);
    });
  }
}
