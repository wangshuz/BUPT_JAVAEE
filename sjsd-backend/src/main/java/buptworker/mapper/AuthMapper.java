package buptworker.mapper;

import buptworker.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Select("SELECT user_id AS userId, username, password " +
            "FROM User WHERE username = #{username}")
    User findByCltname(@Param("username") String username);

    @Select("SELECT merchant_id AS userId, merchant_username AS username, merchant_password AS password " +
            "FROM Merchant WHERE username = #{username}")
    User findByMchname(@Param("username") String username);
}
