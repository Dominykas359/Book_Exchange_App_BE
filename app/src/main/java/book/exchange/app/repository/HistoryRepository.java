package book.exchange.app.repository;

import book.exchange.app.model.Book;
import book.exchange.app.model.History;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface HistoryRepository {

    @Insert("INSERT INTO app.history " +
            "(id, user_id, notice_id, buyer) " +
            "VALUES (#{id}, #{userId}, #{noticeId}, #{buyer})")
    void createHistory(History history);

    @Select("SELECT * FROM app.history WHERE user_id = #{userId} OR buyer = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "noticeId", column = "notice_id"),
            @Result(property = "buyer", column = "buyer")
    })
    List<History> findByUser(@Param("userId") UUID id);

}
