package book.exchange.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    private UUID id;
    private UUID userId;
    private UUID bookId;
    private UUID comicId;
    private UUID periodicalId;
    private LocalDateTime timePosted;
    private String content;
    private UUID comment_id;
    private List<Comment> replies;
}
