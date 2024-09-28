package book.exchange.app.dto.noticeDTOs;

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
public class NoticeRequestDTO {

    private UUID userId;
    private UUID bookId;
    private UUID comicId;
    private UUID periodicalId;
}
