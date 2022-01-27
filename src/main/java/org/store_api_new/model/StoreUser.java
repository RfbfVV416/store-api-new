package org.store_api_new.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Index;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="users", schema = "public",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "email"})},
        indexes = { @Index(columnList = "email")})
public class StoreUser {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue
    private Long id;

    @Enumerated
    private UserRolesProvider role;

    @NotNull
    @Email
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreUser storeUser = (StoreUser) o;
        if (this.email == null || storeUser.email == null) {return false;}
        return Objects.equals(email, storeUser.email);
    }

    @Override
    public int hashCode() {
        return 234343432;
    }
}
