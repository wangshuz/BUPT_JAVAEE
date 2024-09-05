package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 21:58]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 21:58]
 * @updateRemark : [说明本次修改内容]
 */


public class User {
    private Long userId;         // 用户ID
    private String username;     // 用户名
    private String password;     // 密码
    private String profilePicture; // 用户头像

    // 无参构造函数
    public User() {
    }

    // 全参构造函数
    public User(Long userId, String username, String password, String profilePicture) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // Getter 和 Setter 方法
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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