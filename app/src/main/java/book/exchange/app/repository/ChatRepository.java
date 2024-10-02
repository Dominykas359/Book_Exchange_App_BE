package book.exchange.app.repository;

import book.exchange.app.model.Chat;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface ChatRepository {

    @Insert("INSERT INTO app.chats " +
            "(id, user_id, notice_id) " +
            "VALUES(#{id}, #{userId}, #{noticeId})")
    void createChat(Chat chat);

    @Select("SELECT * FROM app.chats WHERE user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "noticeId", column = "notice_id")
    })
    List<Chat> getChatsByUser(@Param("userId") UUID id);

    @Select("SELECT * FROM app.chats WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "noticeId", column = "notice_id")
    })
    Optional<Chat> getChatById(@Param("id") UUID id);

}
