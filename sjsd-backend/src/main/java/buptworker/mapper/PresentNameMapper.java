package buptworker.mapper;

import buptworker.entity.PresentName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PresentNameMapper {
    @Select("SELECT m.merchant_username as name " +
            " from merchant m " +
            " where m.merchant_id=#{id} ")
    public List<PresentName> listMchPresentName(@Param("id") int id);


    @Select("select u.username as name "+
            " from user u "+
            " where u.user_id=#{id} ")
    public List<PresentName> listCltPresentName(@Param("id") int id);
}