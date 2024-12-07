package book.exchange.app.dto.historyDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class HistoryRequestDTO {

    private UUID userId;
    private UUID noticeId;
    private UUID buyer;
}
