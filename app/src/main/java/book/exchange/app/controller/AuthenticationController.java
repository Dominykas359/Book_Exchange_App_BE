package book.exchange.app.controller;

import book.exchange.app.dto.userDTOs.AuthenticationResponseDTO;
import book.exchange.app.dto.userDTOs.LoginRequestDTO;
import book.exchange.app.dto.userDTOs.RegistrationRequestDTO;
import book.exchange.app.dto.userDTOs.UserResponseDTO;
import book.exchange.app.service.AuthenticationService;
import book.exchange.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody RegistrationRequestDTO registrationRequestDTO
    ){
        return ResponseEntity.ok(authenticationService.register(registrationRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody LoginRequestDTO request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<UserResponseDTO> checkEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
