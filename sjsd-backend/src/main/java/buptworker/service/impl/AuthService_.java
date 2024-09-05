package buptworker.service.impl;

import buptworker.entity.User;
import buptworker.mapper.AuthMapper;
import buptworker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 22:23]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 22:23]
 * @updateRemark : [说明本次修改内容]
 */


@Service
public class AuthService_ implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    public User findByCltname(String username, String password) {
        // 查询数据库获取用户信息
        User user = authMapper.findByCltname(username);
        if (user == null) {
            return null;
        }else if(!(password.equals(user.getPassword()))){
            return null;
        }else{
            return user;
        }
    }

    public User findByMchname(String username, String password) {
        // 查询数据库获取用户信息
        User user = authMapper.findByMchname(username);
        if (user == null) {
            return null;
        }else if(!(password.equals(user.getPassword()))){
            return null;
        }else{
            return user;
        }
    }

    @Override
    public boolean registerClt(User clt) {
        int num = authMapper.countByCltname(clt.getUsername());
        if(num > 0){
            return false;
        }
        authMapper.insertClt(clt);
        return true;
    }

    @Override
    public boolean registerMch(User mch) {
        int num = authMapper.countByMchname(mch.getUsername());
        if(num>0){
            return false;
        }
        authMapper.insertMch(mch);
        return true;
    }
}