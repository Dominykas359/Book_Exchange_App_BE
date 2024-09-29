package book.exchange.app.repository;

import book.exchange.app.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface CommentRepository {

    @Insert("INSERT INTO app.comments " +
            "(id, user_id, time_sent, content, comment_id, notice_id) " +
            "VALUES(#{id}, #{userId}, #{timeSent}, #{content}, #{commentId}, #{noticeId})")
    void createComment(Comment comment);

    @Select("SELECT * FROM app.comment WHERE id = #{id}")
    Optional<Comment> findCommentById(@Param("id") UUID id);

    @Select("SELECT * FROM app.comments WHERE notice_id = #{noticeId} AND comment_id IS NULL")
    @Results({
            @Result(property = "id", column = "Id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timeSent", column = "time_sent"),
            @Result(property = "content", column = "content"),
            @Result(property = "commentId", column = "comment_id"),
            @Result(property = "noticeId", column = "notice_id")
    })
    List<Comment> getCommentForNotice(@Param("noticeId") UUID id);


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
