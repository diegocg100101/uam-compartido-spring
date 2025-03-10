package com.uam.uam_compartido_spring.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author diego
 */

@Getter
@Setter
public class changePasswordDTO {
    String email;
    String oldPassword;
    String newPassword;
    String confirmPassword;
}
