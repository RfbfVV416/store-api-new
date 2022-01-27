package org.store_api_new.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CredentialsDto {
    @Email
    private String email;
    @NotBlank
    private String password;
}
