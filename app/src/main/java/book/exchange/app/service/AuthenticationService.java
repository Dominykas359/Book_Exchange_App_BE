package book.exchange.app.service;

import book.exchange.app.dto.userDTOs.AuthenticationResponseDTO;
import book.exchange.app.dto.userDTOs.LoginRequestDTO;
import book.exchange.app.dto.userDTOs.RegistrationRequestDTO;
import book.exchange.app.model.Role;
import book.exchange.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import book.exchange.app.model.User;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponseDTO register(RegistrationRequestDTO request) {

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthday(request.getBirthday())
                .role(Role.USER)
                .build();
        userRepository.createUser(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .role(user.getRole().name())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .role(user.getRole().name())
                .token(jwtToken)
                .build();
    }
}
