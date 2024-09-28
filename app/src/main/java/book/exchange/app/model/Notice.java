package book.exchange.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    private UUID id;
    private UUID userId;
    private UUID bookId;
    private UUID comicId;
    private UUID periodicalId;
    private LocalDate timePosted;
}
