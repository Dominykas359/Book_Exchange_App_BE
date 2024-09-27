package book.exchange.app.repository;

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
            "(id, user_id, time_posted, release_year, title, publisher, author, book_language, status, price, number) " +
            "VALUES(#{id}, #{userId}, #{timePosted}, #{releaseYear}, #{title}, #{publisher}, #{author}, #{bookLanguage}, #{status}, #{price}, #{number})")
    void createPeriodical(Periodical periodical);

    @Select("SELECT * FROM app.periodicals")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findAll();

    @Select("SELECT * FROM app.periodicals WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    Optional<Periodical> findById(@Param("id") UUID id);

    @Select("SELECT * FROM app.periodicals WHERE release_year = #{releaseYear}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByYear(@Param("releaseYear") Integer releaseYear);

    @Select("SELECT * FROM app.periodicals WHERE price <= #{price}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByPrice(@Param("price") Double price);

    @Select("SELECT * FROM app.periodicals WHERE LOWER(title) ILIKE '%' || LOWER(#{title}) || '%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByTitle(@Param("title") String title);

    @Select("SELECT * FROM app.periodicals WHERE LOWER(publisher) ILIKE '%' || LOWER(#{publisher}) || '%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByPublisher(@Param("publisher") String publisher);

    @Select("SELECT * FROM app.periodicals WHERE LOWER(author) ILIKE '%' || LOWER(#{author}) || '%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByAuthor(@Param("author") String author);

    @Select("SELECT * FROM app.periodicals WHERE LOWER(book_language) ILIKE '%' || LOWER(#{bookLanguage}) || '%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByBookLanguage(@Param("bookLanguage") String bookLanguage);

    @Select("SELECT * FROM app.periodicals WHERE LOWER(status) ILIKE '%' || LOWER(#{status}) || '%'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timePosted", column = "time_posted"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "bookLanguage", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "number", column = "number")
    })
    List<Periodical> findByStatus(@Param("status") String status);
}
