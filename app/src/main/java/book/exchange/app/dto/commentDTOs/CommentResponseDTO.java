package book.exchange.app.dto.commentDTOs;

import book.exchange.app.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CommentResponseDTO extends CommentRequestDTO{

    private UUID id;
    private LocalDateTime timeSent;
    private List<CommentResponseDTO> replies;
}
