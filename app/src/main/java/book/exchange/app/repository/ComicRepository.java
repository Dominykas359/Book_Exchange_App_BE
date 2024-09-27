package book.exchange.app.repository;

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
            "(id, user_id, time_posted, release_year, title, publisher, author, book_language, status, price, page_count, colored) " +
            "VALUES(#{id}, #{userId}, #{timePosted}, #{releaseYear}, #{title}, #{publisher}, #{author}, #{bookLanguage}, #{status}, #{price}, #{pageCount}, #{colored})")
    void createComic(Comic comic);

    @Select("SELECT * FROM app.comics")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findAll();

    @Select("SELECT * FROM app.comics WHERE id = #{id}")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    Optional<Comic> findById(@Param("id") UUID id);

    @Select("SELECT * FROM app.comics WHERE release_year = #{releaseYear}")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByYear(@Param("releaseYear") Integer releaseYear);

    @Select("SELECT * FROM app.comics WHERE price <= #{price}")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByPrice(@Param("price") Double price);

    @Select("SELECT * FROM app.comics WHERE LOWER(title) ILIKE '%' || LOWER(#{title}) || '%'")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByTitle(@Param("title") String title);

    @Select("SELECT * FROM app.comics WHERE LOWER(publisher) ILIKE '%' || LOWER(#{publisher}) || '%'")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByPublisher(@Param("publisher") String publisher);

    @Select("SELECT * FROM app.comics WHERE LOWER(author) ILIKE '%' || LOWER(#{author}) || '%'")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByAuthor(@Param("author") String author);

    @Select("SELECT * FROM app.comics WHERE LOWER(book_language) ILIKE '%' || LOWER(#{bookLanguage}) || '%'")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByBookLanguage(@Param("bookLanguage") String bookLanguage);

    @Select("SELECT * FROM app.comics WHERE LOWER(status) ILIKE '%' || LOWER(#{status}) || '%'")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByStatus(@Param("status") String status);

    @Select("SELECT * FROM app.comics WHERE colored = #{colored}")
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
            @Result(property = "pageCount", column = "page_count"),
            @Result(property = "colored", column = "colored")
    })
    List<Comic> findByColored(@Param("colored") Boolean colored);
}
