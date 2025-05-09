package book.exchange.app.dto.userDTOs;

import book.exchange.app.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class RegistrationRequestDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Role role;
}
