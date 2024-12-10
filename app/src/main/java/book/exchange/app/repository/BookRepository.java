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
            "(id, release_year, title, publisher, author, book_language, status, price, page_count, cover, translator) " +
            "VALUES(#{id}, #{releaseYear}, #{title}, #{publisher}, #{author}, #{language}, #{status}, #{price}, #{pageCount}, #{cover}, #{translator})")
    void createBook(Book book);

    @Select("SELECT * FROM app.books")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    List<Book> getAllBooks();

    @Select("SELECT * FROM app.books WHERE id = #{id}")
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
            @Result(property = "cover", column = "cover"),
            @Result(property = "translator", column = "translator")
    })
    Optional<Book> findByBookId(@Param("id") UUID id);

    @Update("UPDATE app.books SET " +
            "release_year = #{releaseYear}, title = #{title}, publisher = #{publisher}, author = #{author}, " +
            "book_language = #{language}, status = #{status}, price = #{price}, page_count = #{pageCount}, cover = #{cover}, " +
            "translator = #{translator} WHERE id = #{id}")
    void updateBook(Book book);

    @Delete("DELETE FROM app.books WHERE id = #{id}")
    void deleteBook(@Param("id") UUID id);
}
