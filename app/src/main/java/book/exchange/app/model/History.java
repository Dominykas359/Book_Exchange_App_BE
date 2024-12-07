package book.exchange.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    private UUID id;
    private UUID noticeId;
    private UUID userId;
    private UUID buyer;
}
