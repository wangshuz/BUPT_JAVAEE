package buptworker.entity;

/**
 * @author : [86183]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/5 22:12]
 * @updateUser : [86183]
 * @updateTime : [2024/9/5 22:12]
 * @updateRemark : [说明本次修改内容]
 */


public class LoginRequest {

    private String username;

    private String password;

    // 无参构造函数
    public LoginRequest() {
    }

    // 全参构造函数
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter 和 Setter 方法
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

    // 可选：toString 方法（调试时有用，但在生产环境中要小心日志中包含敏感信息）
    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}