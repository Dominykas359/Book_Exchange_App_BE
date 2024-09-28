package book.exchange.app.dto.comicDTOs;

import book.exchange.app.dto.publicationDTOs.PublicationRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ComicRequestDTO extends PublicationRequestDTO {

    private Integer pageCount;
    private Boolean colored;
}
