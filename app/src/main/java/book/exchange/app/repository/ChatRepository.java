package book.exchange.app.repository;

import book.exchange.app.model.Chat;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface ChatRepository {

    @Insert("INSERT INTO app.chats " +
            "(id, user_id, book_id, comic_id, periodical_id) " +
            "VALUES(#{id}, #{userId}, #{bookId}, #{comicId}, #{periodicalId})")
    void createChat(Chat chat);

    @Select("SELECT * FROM app.chats WHERE user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id")
    })
    List<Chat> getChatsByUser(@Param("userId") UUID id);

    @Select("SELECT * FROM app.chats WHERE user_id = #{userId} AND book_id = #{bookId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id")
    })
    Optional<Chat> getChatForBook(@Param("userId") UUID userId, @Param("bookId") UUID bookId);

    @Select("SELECT * FROM app.chats WHERE user_id = #{userId} AND comic_id = #{comicId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id")
    })
    Optional<Chat> getChatForComic(@Param("userId") UUID userId, @Param("comicId") UUID comicId);

    @Select("SELECT * FROM app.chats WHERE user_id = #{userId} AND periodical_id = #{periodicalId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id")
    })
    Optional<Chat> getChatForPeriodical(@Param("userId") UUID userID, @Param("periodicalId") UUID periodicalId);
}
