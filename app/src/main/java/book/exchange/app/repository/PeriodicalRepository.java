package book.exchange.app.repository;

import book.exchange.app.model.Comic;
import book.exchange.app.model.Periodical;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface PeriodicalRepository {

    @Insert("INSERT INTO app.periodicals " +
            "(id, release_year, title, publisher, author, book_language, status, price, number) " +
            "VALUES(#{id}, #{releaseYear}, #{title}, #{publisher}, #{author}, #{language}, #{status}, #{price}, #{number})")
    void createPeriodical(Periodical periodical);

    @Select("SELECT * FROM app.periodicals")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "language", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> getAllPeriodicals();

    @Select("SELECT * FROM app.periodicals " +
            "WHERE title ILIKE '%' || #{title} || '%' " +
            "AND status = 'RENTING'")
    List<Periodical> getPeriodicalsByTitle(@Param("title") String title);

    @Select("SELECT * FROM app.periodicals WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "language", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    Optional<Periodical> findPeriodicalById(@Param("id") UUID id);

    @Update("UPDATE app.periodicals SET " +
            "release_year = #{releaseYear}, title = #{title}, publisher = #{publisher}, author = #{author}, " +
            "book_language = #{language}, status = #{status}, price = #{price}, number = #{number} WHERE id = #{id}")
    void updatePeriodical(Periodical periodical);

    @Delete("DELETE FROM app.periodicals WHERE id = #{id}")
    void deletePeriodical(@Param("id") UUID id);
}
