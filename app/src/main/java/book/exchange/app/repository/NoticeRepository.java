package book.exchange.app.repository;

import book.exchange.app.model.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface NoticeRepository {

    @Insert("INSERT INTO app.notices " +
            "(id, user_id, book_id, comic_id, periodical_id, time_posted) " +
            "VALUES(#{id}, #{userId}, #{bookId}, #{comicId}, #{periodicalId}, #{timePosted})")
    void createNotice(Notice notice);

    @Select("SELECT * FROM app.notices")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timePosted", column = "time_posted")
    })
    List<Notice> getAllNotices();

    @Select("SELECT * FROM app.notices WHERE book_id IS NOT NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timePosted", column = "time_posted")
    })
    List<Notice> getAllBookNotices();

    @Select("SELECT * FROM app.notices WHERE comic_id IS NOT NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timePosted", column = "time_posted")
    })
    List<Notice> getAllComicNotices();

    @Select("SELECT * FROM app.notices WHERE periodical_id IS NOT NULL")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timePosted", column = "time_posted")
    })
    List<Notice> getAllPeriodicalNotices();

    @Select("SELECT * FROM app.notices WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timePosted", column = "time_posted")
    })
    Optional<Notice> findById(@Param("id") UUID id);

    @Select("SELECT * FROM app.notices WHERE book_id = #{publicationId} OR comic_id = #{publicationId} OR periodical_id = #{publicationId}")
    List<Notice> findByPublicationId(@Param("publicationId") UUID publicationId);

    @Delete("DELETE FROM app.notices WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timePosted", column = "time_posted")
    })
    void deleteNotice(@Param("id") UUID id);
}
