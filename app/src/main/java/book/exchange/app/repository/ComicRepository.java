package book.exchange.app.repository;

import book.exchange.app.model.Book;
import book.exchange.app.model.Comic;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface ComicRepository {

    @Insert("INSERT INTO app.comics " +
            "(id, release_year, title, publisher, author, book_language, status, price, page_count, colored) " +
            "VALUES(#{id}, #{releaseYear}, #{title}, #{publisher}, #{author}, #{language}, #{status}, #{price}, #{pageCount}, #{colored})")
    void createComic(Comic comic);

    @Select("SELECT * FROM app.comics")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "language", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> getAllComics();

    @Select("SELECT * FROM app.comics " +
            "WHERE title ILIKE '%' || #{title} || '%'")
    List<Comic> getComicsByTitle(@Param("title") String title);

    @Select("SELECT * FROM app.comics WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "releaseYear", column = "release_year"),
            @Result(property = "title", column = "title"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "author", column = "author"),
            @Result(property = "language", column = "book_language"),
            @Result(property = "status", column = "status"),
            @Result(property = "price", column = "price"),
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    Optional<Comic> findComicById(@Param("id") UUID id);

    @Update("UPDATE app.comics SET " +
            "release_year = #{releaseYear}, title = #{title}, publisher = #{publisher}, author = #{author}, " +
            "book_language = #{language}, status = #{status}, price = #{price}, page_count = #{pageCount}, " +
            "colored = #{colored} WHERE id = #{id}")
    void updateComic(Comic comic);

    @Delete("DELETE FROM app.comics WHERE id = #{id}")
    void deleteComic(@Param("id") UUID id);
}
