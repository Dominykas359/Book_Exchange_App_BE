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
public class Publication {

    protected UUID id;
    protected UUID userId;
    protected LocalDate timePosted;
    protected LocalDate releaseYear;
    protected String title;
    protected String publisher;
    protected String author;
    protected String language;
    protected Status status;
    protected Double price;
}
