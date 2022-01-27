package org.store_api_new.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.store_api_new.model.StoreUser;
import org.store_api_new.model.UserRolesProvider;
import org.store_api_new.repository.UserRepository;
import java.util.Collections;
import java.util.List;

import static org.store_api_new.model.UserRolesProvider.ROLE_ADMIN;
import static org.store_api_new.model.UserRolesProvider.ROLE_USER;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return create(
                userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("There is no with with username " + username))
        );
    }

    private UserPrincipal create(StoreUser user){
        List<GrantedAuthority> authorities;
        UserRolesProvider userRole = user.getRole();
        if (ROLE_ADMIN.equals(userRole)) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_ADMIN.toString()));
        } else {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER.toString()));
        }
        return new UserPrincipal(
                authorities,
                user.getEmail(),
                user.getPassword()
        );
    }
}
