package book.exchange.app.repository;

import book.exchange.app.model.WishlistItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface WishlistRepository {
    @Insert("INSERT INTO app.wishlist " +
            "(id, user_id, notice_id) " +
            "VALUES(#{id}, #{user_id}, #{notice_id})")
    void createWishlist(WishlistItem wishlistItem);

    @Select("SELECT * FROM app.wishlist WHERE user_id = #{user_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "notice_id", column = "notice_id")
    })
    List<WishlistItem> getAllWishlist(@Param("user_id") UUID user_id);

    @Delete("DELETE FROM app.wishlist WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "notice_id", column = "notice_id"),
    })
    void deleteWishlist(@Param("id") UUID id);
}
