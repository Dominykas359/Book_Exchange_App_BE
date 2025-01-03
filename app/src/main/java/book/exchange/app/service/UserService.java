package book.exchange.app.service;

import book.exchange.app.dto.userDTOs.AuthenticationResponseDTO;
import book.exchange.app.dto.userDTOs.LoginRequestDTO;
import book.exchange.app.dto.userDTOs.RegistrationRequestDTO;
import book.exchange.app.dto.userDTOs.UserResponseDTO;
import book.exchange.app.mapper.UserMapper;
import book.exchange.app.model.User;
import book.exchange.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<UserResponseDTO> getAllUsers(){

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserResponseDTO findUserById(UUID id){

        return UserMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found")));
    }

    public UserResponseDTO findByEmail(String email){

        return UserMapper.toDto(userRepository.findByUsername(email)
                .orElseThrow(() -> new NoSuchElementException("User with this email not found")));
    }

    @Transactional
    public UserResponseDTO updateUser(UUID id, RegistrationRequestDTO registrationRequestDTO){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setEmail(registrationRequestDTO.getEmail());
        user.setFirstName(registrationRequestDTO.getFirstName());
        user.setLastName(registrationRequestDTO.getLastName());
        user.setBirthday(registrationRequestDTO.getBirthday());
        user.setRole(registrationRequestDTO.getRole());
        userRepository.updateUser(user);
        return UserMapper.toDto(user);
    }

    @Transactional
    public UserResponseDTO changePassword(UUID id, RegistrationRequestDTO registrationRequestDTO){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));
        userRepository.changePassword(user);
        return UserMapper.toDto(user);
    }

    @Transactional
    public void deleteUser(UUID id){
        userRepository.deleteUser(id);
    }
}
