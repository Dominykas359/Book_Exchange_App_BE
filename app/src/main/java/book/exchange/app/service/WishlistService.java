package book.exchange.app.service;

import book.exchange.app.dto.wishlistDTOs.WishlistRequestDTO;
import book.exchange.app.dto.wishlistDTOs.WishlistResponseDTO;
import book.exchange.app.mapper.WishlistMapper;
import book.exchange.app.model.WishlistItem;
import book.exchange.app.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Transactional
    public WishlistResponseDTO createWishlist(WishlistRequestDTO wishlistRequestDTO){

        WishlistItem wishlistItem = WishlistMapper.fromDto(wishlistRequestDTO);
        wishlistRepository.createWishlist(wishlistItem);
        return WishlistMapper.toDto(wishlistItem);
    }

    public List<WishlistResponseDTO> getAllWishlist(UUID user_id){

        return wishlistRepository.getAllWishlist(user_id).stream().map(WishlistMapper::toDto).toList();
    }

    @Transactional
    public void deleteWishlist(UUID id){
        wishlistRepository.deleteWishlist(id);
    }
}
