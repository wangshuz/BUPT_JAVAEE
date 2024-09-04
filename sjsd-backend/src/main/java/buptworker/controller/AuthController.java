package buptworker.controller;

import buptworker.model.User;
import buptworker.model.Merchant;
import buptworker.repository.UserRepository;
import buptworker.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 用户注册
    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // 商家注册
    @PostMapping("/register/merchant")
    public ResponseEntity<String> registerMerchant(@RequestBody Merchant merchant) {
        if (merchantRepository.findByMerchantUsername(merchant.getMerchantUsername()) != null) {
            return ResponseEntity.badRequest().body("Merchant username already exists");
        }
        merchant.setMerchantPassword(passwordEncoder.encode(merchant.getMerchantPassword()));
        merchantRepository.save(merchant);
        return ResponseEntity.ok("Merchant registered successfully");
    }

    // 用户登录
    @PostMapping("/login/user")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest, HttpServletResponse response) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        Cookie cookie = new Cookie("user_id", String.valueOf(user.getUserId()));
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("User logged in successfully");
    }

    // 商家登录
    @PostMapping("/login/merchant")
    public ResponseEntity<String> loginMerchant(@RequestBody Merchant loginRequest, HttpServletResponse response) {
        Merchant merchant = merchantRepository.findByMerchantUsername(loginRequest.getMerchantUsername());
        if (merchant == null || !passwordEncoder.matches(loginRequest.getMerchantPassword(), merchant.getMerchantPassword())) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
        Cookie cookie = new Cookie("merchant_id", String.valueOf(merchant.getMerchantId()));
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("Merchant logged in successfully");
    }
}

