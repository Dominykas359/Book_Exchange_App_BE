package book.exchange.app.repository;

import book.exchange.app.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface CommentRepository {

    @Insert("INSERT INTO app.comments " +
            "(id, user_id, book_id, comic_id, periodical_id, time_sent, content, comment_id) " +
            "VALUES(#{id}, #{userId}, #{bookId}, #{comicId}, #{periodicalId}, #{timeSent}, #{content}, #{commentId})")
    void createComment(Comment comment);

    @Select("SELECT * FROM app.comments WHERE book_id = #{bookId} AND comment_id IS NULL")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timeSent", column = "time_sent"),
            @Result(property = "content", column = "content"),
            @Result(property = "commentId", column = "comment_id")
    })
    List<Comment> getCommentForBook(@Param("bookI") UUID id);

    @Select("SELECT * FROM app.comments WHERE comic_id = #{comicId} AND comment_id IS NULL")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timeSent", column = "time_sent"),
            @Result(property = "content", column = "content"),
            @Result(property = "commentId", column = "comment_id")
    })
    List<Comment> getCommentForComic(@Param("comicId") UUID id);

    @Select("SELECT * FROM app.comments WHERE periodical_id = #{periodicalId} AND comment_id IS NULL")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "comicId", column = "comic_id"),
            @Result(property = "periodicalId", column = "periodical_id"),
            @Result(property = "timeSent", column = "time_sent"),
            @Result(property = "content", column = "content"),
            @Result(property = "commentId", column = "comment_id")
    })
    List<Comment> getCommentForPeriodical(@Param("periodicalId") UUID id);

    @Select("SELECT * FROM app.comments WHERE comment_id = #{commentId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "timePosted", column = "time_sent"),
            @Result(property = "content", column = "content"),
            @Result(property = "commentId", column = "comment_id")
    })
    List<Comment> getRepliesForComment(@Param("commentId") UUID commentId);

    @Update("UPDATE app.comment SET " +
            "time_posted = #{timePosted}, content = #{content} " +
            "WHERE id = #{id}")
    void updateComment(Comment comment);

    @Delete("DELETE FROM app.comments WHERE id = #{id}")
    void deleteComment(@Param("id") UUID id);
}
