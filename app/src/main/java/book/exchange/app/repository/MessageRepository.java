package book.exchange.app.repository;

import book.exchange.app.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface MessageRepository {

    @Insert("INSERT INTO app.messages " +
            "(id, user_id, chat_id, text, time_sent) " +
            "VALUES(#{id}, #{userId}, #{chatId}, #{timeSent})")
    void createMessage(Message message);

    @Select("SELECT * FROM app.messages WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chatId", column = "chat_id"),
            @Result(property = "text", column = "text"),
            @Result(property = "timeSent", column = "time_sent")
    })
    Optional<Message> findMessageById(@Param("id") UUID id);

    @Select("SELECT * FROM app.messages WHERE chat_id = #{chatId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "chatId", column = "chat_id"),
            @Result(property = "text", column = "text"),
            @Result(property = "timeSent", column = "time_sent")
    })
    List<Message> getAllMessages(@Param("chatId") UUID id);

    @Update("UPDATE app.messages SET " +
            "text = #{text}, time_sent = #{timeSent} WHERE id = #{id}")
    void updateMessage(Message message);

    @Delete("DELETE FROM app.messages WHERE id = #{id}")
    void deleteMessage(@Param("id") UUID id);
}
