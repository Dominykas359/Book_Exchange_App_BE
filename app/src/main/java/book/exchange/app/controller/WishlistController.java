package book.exchange.app.controller;

import book.exchange.app.dto.wishlistDTOs.WishlistRequestDTO;
import book.exchange.app.dto.wishlistDTOs.WishlistResponseDTO;
import book.exchange.app.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;
    @PostMapping
    public ResponseEntity<WishlistResponseDTO> createWishlist(
            @RequestBody WishlistRequestDTO wishlistRequestDTO
    ){
        return ResponseEntity.ok(wishlistService.createWishlist(wishlistRequestDTO));
    }

    @GetMapping("/wishlists/{user_id}")
    public List<WishlistResponseDTO> getAllWishlist(@PathVariable("user_id") UUID user_id) {
        return wishlistService.getAllWishlist(user_id);
    }

    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable("id") UUID id){
        wishlistService.deleteWishlist(id);
    }
}
