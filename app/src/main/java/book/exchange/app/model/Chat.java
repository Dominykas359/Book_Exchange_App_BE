package book.exchange.app.model;

import book.exchange.app.dto.messageDTOs.MessageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    private UUID id;
    private UUID userId;
    private UUID noticeId;
    private List<Message> messages;
    private UUID receiver;
}
