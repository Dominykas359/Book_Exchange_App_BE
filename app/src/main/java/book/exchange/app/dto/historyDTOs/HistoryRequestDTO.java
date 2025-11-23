package book.exchange.app.dto.historyDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class HistoryRequestDTO {

    @NotNull
    private UUID userId;
    @NotNull
    private UUID noticeId;
    @NotNull
    private UUID buyer;
}
