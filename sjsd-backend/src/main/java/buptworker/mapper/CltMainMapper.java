package buptworker.mapper;

import buptworker.pojo.MchIntro;
import buptworker.pojo.MchType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //运行时会自动生成该接口的实现类对象（代理对象），并交给IOC管理
public interface CltMainMapper {
    @Select("SELECT merchant_id as id,merchant_username as name,merchant_description as intro,type_id as label,merchant_avatar as url FROM merchant")
    public List<MchIntro> listMchIntro();

    @Select("SELECT type_id AS typeId, type_name AS typeName FROM merchant_type")
    public List<MchType> listMchType();
}
