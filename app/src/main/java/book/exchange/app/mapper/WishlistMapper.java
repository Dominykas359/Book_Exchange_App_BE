package book.exchange.app.mapper;

import book.exchange.app.dto.wishlistDTOs.WishlistRequestDTO;
import book.exchange.app.dto.wishlistDTOs.WishlistResponseDTO;
import book.exchange.app.model.WishlistItem;
import java.util.UUID;

public class WishlistMapper {

    public static WishlistResponseDTO toDto(WishlistItem wishlistitem){

        return WishlistResponseDTO.builder()
                .id(wishlistitem.getId())
                .user_id(wishlistitem.getUser_id())
                .notice_id(wishlistitem.getNotice_id())
                .build();
    }

    public static WishlistItem fromDto(WishlistRequestDTO wishlistRequestDTO){

        return WishlistItem.builder()
                .id(UUID.randomUUID())
                .user_id(wishlistRequestDTO.getUser_id())
                .notice_id(wishlistRequestDTO.getNotice_id())
                .build();
    }
}
