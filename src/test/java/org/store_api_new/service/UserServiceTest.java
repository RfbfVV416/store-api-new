package org.store_api_new.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.store_api_new.exception.UserAlreadyRegisteredException;
import org.store_api_new.model.StoreUser;
import org.store_api_new.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerExistedUserThrowEx() {
        StoreUser user = new StoreUser();
        user.setEmail("abcdef@mail.ru");
        user.setPassword("Test_password");
        doReturn(true).when(userRepository).existsByEmail(user.getEmail());

        assertThrows(UserAlreadyRegisteredException.class, () -> userService.register(user));
    }
}