package buptworker.service;

import buptworker.entity.Password;

public interface PasswordService {
    public void CltPassword(String password,Integer id);
    public void MchPassword(String password,Integer id);
}
