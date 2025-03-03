package com.uam.uam_compartido_spring.Service;

import com.uam.uam_compartido_spring.DTO.AuthRequestDTO;
import com.uam.uam_compartido_spring.DTO.AuthResponseDTO;
import com.uam.uam_compartido_spring.Model.Rol;
import com.uam.uam_compartido_spring.Model.Usuario;
import com.uam.uam_compartido_spring.Repository.RolRepository;
import com.uam.uam_compartido_spring.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author diego
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;

    public AuthResponseDTO signup(Usuario usuario) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(rolRepository.getReferenceById(1));
        usuarioRepository.save(usuario);
        String jwtToken = jwtService.generateToken(usuario);
        return authResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDTO authenticate(AuthRequestDTO authDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getEmail(),
                        authDTO.getPassword()
                )
        );
        Usuario usuario = usuarioRepository.findByEmail(authDTO.getEmail())
                .orElseThrow();
        return AuthResponseDTO
                .builder()
                .token(jwtService.generateToken(usuario))
                .build();
    }

    public boolean changePassword(String email, String inputPassword, String newPassword) {
        Usuario usuario = usuarioRepository.findByEmail(email).get();
        String oldPassword = usuario.getPassword();

        if(passwordEncoder.matches(inputPassword, oldPassword)) {
            usuario.setPassword(passwordEncoder.encode(newPassword));
            usuarioRepository.save(usuario);
            return true;
        }

        return false;
    }
}
