package book.exchange.app.controller;

import book.exchange.app.dto.userDTOs.RegistrationRequestDTO;
import book.exchange.app.dto.userDTOs.UserResponseDTO;
import book.exchange.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable("id") UUID id,
            @RequestBody RegistrationRequestDTO registrationRequestDTO
    ){
        return userService.updateUser(id, registrationRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") UUID id){
        userService.deleteUser(id);
    }
}
