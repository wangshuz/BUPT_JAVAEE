package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 21:58]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 21:58]
 * @updateRemark : [说明本次修改内容]
 */


public class User {
    private String username;     // 用户名
    private String password;     // 密码

    // 无参构造函数
    public User() {
    }

    // 全参构造函数
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}