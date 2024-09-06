package buptworker.mapper;

import buptworker.entity.Password;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PasswordMapper {
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{id}")
    public void CltPassword(@Param("password") String password,@Param("id") Integer id);

    @Update("UPDATE merchant SET merchant_password = #{password} WHERE merchant_id = #{id}")
    public void MchPassword(@Param("password") String password,@Param("id") Integer id);
}
