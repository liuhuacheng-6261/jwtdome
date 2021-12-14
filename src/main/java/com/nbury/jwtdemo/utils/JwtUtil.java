package com.nbury.jwtdemo.utils;

import com.nbury.jwtdemo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author liuhuacheng
 * @version 1.0
 * @description com.nbury.jwtdemo.utils
 * @date 2021/12/14
 */
public class JwtUtil {

    private static SecretKey generalKey(){

        String stringKey = "123456";
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;
    }

    /**
     * 根据用户信息为其签发token
     */

    public static String generalToken(User user) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            SecretKey key = generalKey();

            Map<String, Object> claims = new HashMap<>(16);
            claims.put("userId", user.getId());
            claims.put("username", user.getUsername());

            long nowMillis = System.currentTimeMillis();
            Date nowTime = new Date(nowMillis);

            long expMillis = nowMillis + 10 * 60 * 1000;
            Date expTime = new Date(expMillis);

            JwtBuilder jwtBuilder = Jwts.builder()
                    .setClaims(claims)
                    .setId(UUID.randomUUID().toString())
                    .setIssuer("jwtdemo")
                    .setIssuedAt(nowTime)
                    .setExpiration(expTime)
                    .setSubject("user")
                    .signWith(signatureAlgorithm,key);
            return jwtBuilder.compact();
        } catch (Exception e) {
            e.printStackTrace();
            return "生成token失败";
        }

    }

    public static Claims parseToken(String token) {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
