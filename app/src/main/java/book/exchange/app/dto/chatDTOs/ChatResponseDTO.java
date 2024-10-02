package book.exchange.app.dto.chatDTOs;

import book.exchange.app.dto.messageDTOs.MessageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ChatResponseDTO extends ChatRequestDTO{

    private UUID id;
    private List<MessageResponseDTO> messages;
}
