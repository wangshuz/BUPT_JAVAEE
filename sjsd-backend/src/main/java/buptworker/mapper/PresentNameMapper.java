package buptworker.mapper;

import buptworker.entity.PresentName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PresentNameMapper {
    @Select("SELECT m.merchant_username as personName " +
            " from merchant m " +
            " where m.merchant_id=#{id} ")
    public PresentName listMchPresentName(@Param("id") int id);


    @Select("select u.username as personName "+
            " from user u "+
            " where u.user_id=#{id} ")
    public PresentName listCltPresentName(@Param("id") int id);
}
