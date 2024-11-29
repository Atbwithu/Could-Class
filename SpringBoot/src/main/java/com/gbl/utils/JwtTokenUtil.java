package com.gbl.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    // 令牌密钥，应保密并存储在安全的地方
    private static final String SECRET_KEY = "your_secret_key";

    // 用于设置token的过期时间，单位为毫秒，默认为1小时（可根据需求修改）
    private static long expirationTime = 3600000L;

    // 设置token的过期时间
    public static void setExpirationTime(long newExpirationTime) {
        expirationTime = newExpirationTime;
    }

    // 从token中获取用户名
    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // 检查token是否过期
    public static boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    // 生成token，可传入自定义声明信息
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 从token中获取所有声明信息
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}