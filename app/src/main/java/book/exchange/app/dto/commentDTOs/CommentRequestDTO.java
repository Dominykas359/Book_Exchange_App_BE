package book.exchange.app.dto.commentDTOs;

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
public class CommentRequestDTO {

    private UUID userID;
    private LocalDateTime timeSent;
    private String content;
    private UUID commentId;
    private UUID noticeId;
}
