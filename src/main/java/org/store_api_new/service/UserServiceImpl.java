package org.store_api_new.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.store_api_new.config.WebSecurityProperties;
import org.store_api_new.constant.SessionAttributeName;
import org.store_api_new.exception.NotFoundEntityException;
import org.store_api_new.exception.UserAlreadyRegisteredException;
import org.store_api_new.model.StoreUser;
import org.store_api_new.model.UserRolesProvider;
import org.store_api_new.repository.UserRepository;
import javax.servlet.http.HttpSession;
import static org.store_api_new.model.UserRolesProvider.ROLE_USER;
import static org.store_api_new.model.UserRolesProvider.ROLE_ADMIN;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final WebSecurityProperties properties;

    @Override
    public StoreUser register(StoreUser user) {
        String email = user.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new UserAlreadyRegisteredException(email + " already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(determineRole(email));
        return userRepository.save(user);
    }

    @Override
    public StoreUser getUserBySessionEmail(HttpSession httpSession){
        return userRepository.findByEmail((String) httpSession.getAttribute(SessionAttributeName.USER))
                .orElseThrow(() -> new NotFoundEntityException("User not found"));
    }

    private UserRolesProvider determineRole(String email){
        if (properties.getAdminEmail().equals(email)){
            return ROLE_ADMIN;
        }
        return ROLE_USER;
    }
}
