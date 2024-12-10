package book.exchange.app.repository;

import book.exchange.app.model.Filters;
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

    @Select("SELECT DISTINCT n.* " +
            "FROM app.notices n " +
            "LEFT JOIN app.books b ON n.book_id = b.id " +
            "LEFT JOIN app.comics c ON n.comic_id = c.id " +
            "LEFT JOIN app.periodicals p ON n.periodical_id = p.id " +
            "WHERE " +
            "    (COALESCE(#{userId}, '') IS NULL OR n.user_id = #{userId})" +
            "    AND (COALESCE(#{book}, false) = false OR (#{book} = true AND n.book_id IS NOT NULL))" +
            "    AND (COALESCE(#{comic}, false) = false OR (#{comic} = true AND n.comic_id IS NOT NULL))" +
            "    AND (COALESCE(#{periodical}, false) = false OR (#{periodical} = true AND n.periodical_id IS NOT NULL))" +
            "    AND (COALESCE(#{author}, '') IS NULL OR (b.author ILIKE '%' || #{author} || '%' " +
            "                                           OR c.author ILIKE '%' || #{author} || '%' " +
            "                                           OR p.author ILIKE '%' || #{author} || '%'))" +
            "    AND (COALESCE(#{title}, '') IS NULL OR (b.title ILIKE '%' || #{title} || '%' " +
            "                                          OR c.title ILIKE '%' || #{title} || '%' " +
            "                                          OR p.title ILIKE '%' || #{title} || '%'))" +
            "    AND (#{fromDate} IS NULL OR n.time_posted >= #{fromDate})" +
            "    AND (#{toDate} IS NULL OR n.time_posted <= #{toDate})" +
            "    AND (#{fromPrice} IS NULL OR (b.price >= #{fromPrice} " +
            "                                  OR c.price >= #{fromPrice} " +
            "                                  OR p.price >= #{fromPrice}))" +
            "    AND (#{toPrice} IS NULL OR (b.price <= #{toPrice} " +
            "                                OR c.price <= #{toPrice} " +
            "                                OR p.price <= #{toPrice}))" +
            "    AND (COALESCE(#{language}, '') IS NULL OR (b.book_language = #{language} " +
            "                                              OR c.book_language = #{language} " +
            "                                              OR p.book_language = #{language}))" +
            "    AND (COALESCE(#{status}, '') IS NULL OR (b.status = #{status} " +
            "                                            OR c.status = #{status} " +
            "                                            OR p.status = #{status}))" +
            "    AND (COALESCE(#{cover}, '') IS NULL OR (b.cover = #{cover}));")
    List<Notice> filteredNotices(Filters filters);


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
