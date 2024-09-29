package book.exchange.app.dto.periodicalDTOs;

import book.exchange.app.dto.publicationDTOs.PublicationRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PeriodicalRequestDTO extends PublicationRequestDTO {

    private Integer number;
}
