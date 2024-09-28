package book.exchange.app.dto.periodicalDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PeriodicalResponseDTO extends PeriodicalRequestDTO{

    private UUID id;
}
