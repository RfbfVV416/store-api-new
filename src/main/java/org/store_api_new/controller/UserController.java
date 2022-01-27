package org.store_api_new.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.store_api_new.model.StoreUser;
import org.store_api_new.service.UserService;
import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public StoreUser registerUser(@RequestBody @Valid StoreUser user){
        return userService.register(user);
    }
}
