package book.exchange.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Book extends Publication{

    private Integer pageCount;
    private String cover;
    private String translator;
}
