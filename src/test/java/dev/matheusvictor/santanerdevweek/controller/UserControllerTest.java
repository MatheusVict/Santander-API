package dev.matheusvictor.santanerdevweek.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.matheusvictor.santanerdevweek.domain.model.Account;
import dev.matheusvictor.santanerdevweek.domain.model.User;
import dev.matheusvictor.santanerdevweek.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  @Mock
  UserService userService;

  @InjectMocks
  UserController userController;

  MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testFindByIdReturnsUser() throws Exception {
    User user = new User();
    user.setId(1L);

    when(userService.findById(1L)).thenReturn(user);

    mockMvc.perform(get("/users/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void testSaveReturnsUser() throws Exception {
    User userToCreate = new User();
    Account account = new Account();
    account.setNumber("123456");
    userToCreate.setAccount(account);

    User savedUser = new User();
    savedUser.setId(1L);
    savedUser.setAccount(account);

    when(userService.save(userToCreate)).thenReturn(savedUser);

    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userToCreate)))
            .andExpect(status().isCreated());
  }
}
