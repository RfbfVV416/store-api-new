package org.store_api_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.store_api_new.model.StoreUser;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<StoreUser, Long> {
    Optional<StoreUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
