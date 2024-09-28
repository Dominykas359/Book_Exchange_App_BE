package book.exchange.app.dto.bookDTOs;

import book.exchange.app.dto.publicationDTOs.PublicationRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BookRequestDTO extends PublicationRequestDTO {

    private Integer pageCount;
    private String cover;
    private String translator;
}
