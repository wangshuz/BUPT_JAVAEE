package buptworker.service;

import buptworker.entity.User;

public interface AuthService {
    public User findByCltname(String username, String password);
    public User findByMchname(String username, String password);
}
