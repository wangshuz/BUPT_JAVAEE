package buptworker.service.impl;

import buptworker.entity.Password;
import buptworker.mapper.PasswordMapper;
import buptworker.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordService_ implements PasswordService {
    @Autowired
    private PasswordMapper passwordMapper;

    @Override
    public void CltPassword(String password,Integer id)
    {
        passwordMapper.CltPassword(password,id);
    }

    @Override
    public void MchPassword(String password,Integer id)
    {
        passwordMapper.MchPassword(password,id);
    }
}
