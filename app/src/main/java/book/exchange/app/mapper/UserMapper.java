package book.exchange.app.mapper;

import book.exchange.app.dto.userDTOs.RegistrationRequestDTO;
import book.exchange.app.dto.userDTOs.UserResponseDTO;
import book.exchange.app.model.Role;
import book.exchange.app.model.User;

import java.util.UUID;

public class UserMapper {

    public static UserResponseDTO toDto(User user){

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .build();
    }

    public static User fromDto(RegistrationRequestDTO registrationRequestDTO){

        return User.builder()
                .id(UUID.randomUUID())
                .username(registrationRequestDTO.getUsername())
                .password(registrationRequestDTO.getPassword())
                .firstName(registrationRequestDTO.getFirstName())
                .lastName(registrationRequestDTO.getLastName())
                .birthday(registrationRequestDTO.getBirthday())
                .role(Role.USER)
                .build();
    }
}
