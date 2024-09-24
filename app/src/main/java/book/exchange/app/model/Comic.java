package book.exchange.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Comic extends Publication{

    private Integer pageCount;
    private boolean colored;
}
