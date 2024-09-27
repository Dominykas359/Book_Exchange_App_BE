package book.exchange.app.repository;

import book.exchange.app.model.Book;
import book.exchange.app.model.History;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface HistoryRepository {

    @Insert("INSERT INTO app.history " +
            "(id, book_id, comic_id, periodical_id, user_id) " +
            "VALUES (#{id}, #{bookId}, #{comicId}, #{periodicalId}, #{userId})")
    void createHistory(History history);

    @Select("SELECT * FROM app.history WHERE user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "userId", column = "user_id")
    })
    List<History> findByUser(@Param("userId") UUID id);

}
