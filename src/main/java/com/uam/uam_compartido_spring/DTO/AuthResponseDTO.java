package com.uam.uam_compartido_spring.DTO;

import lombok.*;

/**
 * @author diego
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    public String token;
}
