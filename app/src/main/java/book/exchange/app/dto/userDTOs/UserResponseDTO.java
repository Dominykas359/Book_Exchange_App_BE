package book.exchange.app.dto.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserResponseDTO {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String role;
}
