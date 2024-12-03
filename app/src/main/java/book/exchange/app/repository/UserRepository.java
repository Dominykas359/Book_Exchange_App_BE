package book.exchange.app.repository;

import book.exchange.app.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO app.users " +
            "(id, email, password, first_name, last_name, birthday, role) " +
            "VALUES(#{id}, #{email}, #{password}, #{firstName}, #{lastName}, #{birthday}, #{role})")
    void createUser(User user);

    @Select("SELECT * FROM app.users")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "role", column = "role")
    })
    List<User> findAll();

    @Select("SELECT * FROM app.users WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "role", column = "role")
    })
    Optional<User> findById(@Param("id") UUID id);

    @Select("SELECT * FROM app.users WHERE email = #{email}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "role", column = "role")
    })
    Optional<User> findByUsername(@Param("email") String email);

    @Update("UPDATE app.users SET " +
            "email = #{email}, first_name = #{firstName}, last_name = #{lastName}, " +
            "birthday = #{birthday} " +
            "WHERE id = #{id}")
    void updateUser(User user);

    @Update("UPDATE app.users SET " +
            "password = #{password} WHERE id = #{id}")
    void changePassword(User user);

    @Delete("DELETE FROM app.users WHERE id = #{id}")
    void deleteUser(@Param("id") UUID id);
}
