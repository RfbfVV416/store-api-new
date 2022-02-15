package org.store_api_new.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.store_api_new.model.StoreUser;
import org.store_api_new.repository.UserRepository;
import org.store_api_new.service.UserService;
import javax.transaction.Transactional;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.store_api_new.model.UserRolesProvider.ROLE_USER;

@Transactional
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource("/application.yml")
class UserServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void registerNewUser() {
        StoreUser user = new StoreUser();
        user.setEmail("abcdef@mail.ru");
        user.setPassword("Test_password");

        StoreUser returnedUser = userService.register(user);

        assertNotNull(returnedUser);
        assertNotNull(returnedUser.getId());
        assertEquals(user.getEmail(), returnedUser.getEmail());
        assertTrue(is(returnedUser.getRole()).matches(ROLE_USER));
    }
}