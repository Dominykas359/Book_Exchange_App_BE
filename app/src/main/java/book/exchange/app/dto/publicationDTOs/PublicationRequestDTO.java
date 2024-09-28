package book.exchange.app.dto.publicationDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class PublicationRequestDTO {

    private LocalDate releaseYear;
    private String title;
    private String publisher;
    private String author;
    private String language;
    private String status;
    private Double price;
}
