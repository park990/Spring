package com.sist.ex0918.global.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    @Value("${custom.jwt.secretKey}")
    private String secretKeyCode;

    private SecretKey secretKey;

    // application.secret.yml에 있는 secretKeyCode를 암호한 한것.
    public SecretKey getSecretKey() {
        if (secretKey == null) {
            String encoding = Base64.getEncoder()
                    .encodeToString(secretKeyCode.getBytes());

            // secretKey는 숫자와 문자들을 어떠한 규칙없이 마음대로 기술한 것
            // 그 값(secretKeyCode)을 가지고 jwt키를 만들어야 한다 이때
            // jjwt라는 라이브러리를 사용하면 편하다
            secretKey = Keys.hmacShaKeyFor(encoding.getBytes());
        }
        return secretKey;
    }

    public String genToken(Map<String, Object> map, int seconds) {
        Long now = new Date().getTime();

        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);
        // 1000L * seconds : 밀리초로 변환 -> 만료시간

        JwtBuilder jwtBuilder = Jwts.builder().subject("SIST")
                .expiration(accessTokenExpiresIn);
        Set<String> keys = map.keySet(); // 반족자 처리하기 위해 Set구조화
        Iterator<String> it = keys.iterator();
    
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);

            jwtBuilder.claim(key, value);
            /*
             * JWT(JSON Web Token)은 크게 3가지 영역으로 나뉘어 있다
             * 1. Header(알고리즘, 타입)
             * 2. Payload(실제 토큰에 담긴 데이터)
             * 3. Signature(서명)
             * 
             * 이 중 Payload 안에 들어있는 정보 (data) 단위를 Claim이라 한다
             */
        } // for
        String jwt = jwtBuilder.signWith(getSecretKey()).compact();

        return jwt;
    }

    // 토큰이 만료되었는지 확인하는 메서드
    public boolean verify(String token) {
        boolean value = true;

        try {
            Jwts.parser().verifyWith(getSecretKey())
                    .build().parseSignedClaims(token);
        } catch (Exception e) {
            // 유효기간이 만료되면 예외 발생됨
            value = false;
        }

        return value;
    }

    // 토큰에 담긴 사용자정보(claims)를 반환한다
    public Map<String, Object> getClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
