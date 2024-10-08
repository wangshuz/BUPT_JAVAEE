package buptworker.mapper;

import buptworker.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Select("SELECT user_id AS userId, username, password " +
            "FROM User WHERE username = #{username}")
    User findByCltname(@Param("username") String username);

    @Select("SELECT merchant_id AS userId, merchant_username AS username, merchant_password AS password " +
            "FROM Merchant WHERE merchant_username = #{username}")
    User findByMchname(@Param("username") String username);

    // 检查用户名是否已存在
    @Select("SELECT COUNT(*) FROM User WHERE username = #{username}")
    int countByCltname(@Param("username") String username);

    // 插入新用户
    @Insert("INSERT INTO User (username, password) VALUES (#{username}, #{password})")
    void insertClt(User user);


    @Select("SELECT COUNT(*) FROM Merchant WHERE merchant_username = #{username}")
    int countByMchname(@Param("username") String username);


    @Insert("INSERT INTO Merchant (merchant_username, merchant_password) VALUES (#{username}, #{password})")
    void insertMch(User user);
}
