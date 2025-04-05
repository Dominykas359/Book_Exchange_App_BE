package book.exchange.app.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends Publication{

    private Integer pageCount;
    private String cover;
    private String translator;
}
