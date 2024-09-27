package book.exchange.app.repository;

import book.exchange.app.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface BookRepository {

    @Insert("INSERT INTO app.books " +
            "(id, user_id, time_posted, release_year, title, publisher, author, book_language, status, price, page_count, cover, translator) " +
            "VALUES(#{id}, #{userId}, #{timePosted}, #{releaseYear}, #{title}, #{publisher}, #{author}, #{bookLanguage}, #{status}, #{price}, #{pageCount}, #{cover}, #{translator})")
    void createBook(Book book);

    @Select("SELECT * FROM app.books")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findAll();

    @Select("SELECT * FROM app.books WHERE id = #{id}")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    Optional<Book> findById(@Param("id") UUID id);

    @Select("SELECT * FROM app.books WHERE release_year = #{releaseYear}")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByYear(@Param("releaseYear") Integer releaseYear);

    @Select("SELECT * FROM app.books WHERE price <= #{price}")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByPrice(@Param("price") Double price);

    @Select("SELECT * FROM app.books WHERE LOWER(title) ILIKE '%' || LOWER(#{title}) || '%'")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByTitle(@Param("title") String title);

    @Select("SELECT * FROM app.books WHERE LOWER(publisher) ILIKE '%' || LOWER(#{publisher}) || '%'")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByPublisher(@Param("publisher") String publisher);

    @Select("SELECT * FROM app.book WHERE LOWER(author) ILIKE '%' || LOWER(#{author}) || '%'")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByAuthor(@Param("author") String author);

    @Select("SELECT * FROM app.books WHERE LOWER(book_language) ILIKE '%' || LOWER(#{bookLanguage}) || '%'")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByBookLanguage(@Param("bookLanguage") String bookLanguage);

    @Select("SELECT * FROM app.books WHERE LOWER(status) ILIKE '%' || LOWER(#{status}) || '%'")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> findByStatus(@Param("status") String status);
}
