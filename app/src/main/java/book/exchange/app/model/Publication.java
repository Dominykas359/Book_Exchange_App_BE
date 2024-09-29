package book.exchange.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Publication {

    protected UUID id;
    protected LocalDate releaseYear;
    protected String title;
    protected String publisher;
    protected String author;
    protected String language;
    protected Status status;
    protected Double price;
}
