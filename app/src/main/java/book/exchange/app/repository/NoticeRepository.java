package book.exchange.app.repository;

import book.exchange.app.model.Notice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface NoticeRepository {

    @Insert("INSERT INTO app.notices " +
            "(id, user_id, book_id, comic_id, periodical_id, time_posted) " +
            "VALUES(#{id}, #{userId}, #{bookId}, #{comicId}, #{periodicalId}, #{timePosted})")
    void createNotice(Notice notice);

    @Select("SELECT * FROM app.notices")
    List<Notice> getAllNotices();

    @Select("SELECT * FROM app.notices WHERE book_id IS NOT NULL")
    List<Notice> getAllBookNotices();

    @Select("SELECT * FROM app.notices WHERE comic_id IS NOT NULL")
    List<Notice> getAllComicNotices();

    @Select("SELECT * FROM app.notices WHERE periodical_id IS NOT NULL")
    List<Notice> getAllPeriodicalNotices();

    @Delete("DELETE FROM app.notices WHERE id = #{id}")
    void deleteNotice(@Param("id") UUID id);
}
