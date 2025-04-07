package book.exchange.app.model;

import lombok.*;

import java.util.UUID;
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class WishlistItem {
    UUID id;
    UUID user_id;
    UUID notice_id;

    public WishlistItem(UUID uuid, UUID userId, UUID noticeId) {
    }
}
