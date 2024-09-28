package book.exchange.app.dto.messageDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class MessageRequestDTO {

    private UUID userId;
    private UUID chatId;
    private String text;
    private LocalDateTime timeSent;
}
