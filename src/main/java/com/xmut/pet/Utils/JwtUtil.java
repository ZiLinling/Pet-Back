package com.xmut.pet.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.xmut.pet.entity.User;

import java.util.Date;

public class JwtUtil {
    private static Algorithm algorithm = Algorithm.HMAC256("asdwqewqhidhwhdqwh");
    static public String generateToken(User user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000 * 24;//24小时
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create()
                .withClaim("userid", user.getId())
//                .withClaim("username",user.getUsername())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(algorithm);
        return token;
    }

    static public Integer parseToken(String token, String key) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token).getClaim(key).asInt();
    }

    static public Integer getUserId(String token) {
        return parseToken(token, "userid");
    }

    static public void checkToken(String token) {
        if (token == null) {
            token = "";
        }
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        jwtVerifier.verify(token);
    }
}