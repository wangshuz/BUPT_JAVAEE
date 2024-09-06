package buptworker.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionUtil {

    // 用来存储 sessionId 对应的用户/商家ID及类型
    private static final Map<String, SessionData> sessionMap = new HashMap<>();

    // 创建 session 并存储 sessionId
    public static void createSession(HttpServletResponse response, Long userId, String userType) {
        String sessionId = UUID.randomUUID().toString();
        sessionMap.put(sessionId, new SessionData(userId, userType));

        // 设置 Cookie
        Cookie cookie = new Cookie("sessionId", sessionId);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    // 获取 session 数据（用户ID和类型）
    public static SessionData getSessionData(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    return sessionMap.get(sessionId);
                }
            }
        }
        return null;
    }

    // 获取 用户ID
    public static Long getUserID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    return sessionMap.get(sessionId).id;
                }
            }
        }
        return null;
    }

    // 删除 session 和对应的 Cookie
    public static void deleteSession(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    String sessionId = cookie.getValue();
                    sessionMap.remove(sessionId);

                    // 清除 Cookie
                    Cookie sessionCookie = new Cookie("sessionId", null);
                    sessionCookie.setMaxAge(0);
                    sessionCookie.setPath("/");
                    response.addCookie(sessionCookie);
                    break;
                }
            }
        }
    }

    // 内部类保存 sessionId 对应的用户ID及类型
    public static class SessionData {
        private Long id;
        private String type;

        public SessionData(Long id, String type) {
            this.id = id;
            this.type = type;
        }

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }
    }
}
