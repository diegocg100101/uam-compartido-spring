package com.uam.uam_compartido_spring.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author diego
 */

@Getter
@Setter
@RequiredArgsConstructor
public class AuthRequestDTO {
    public String email;
    public String password;
}
