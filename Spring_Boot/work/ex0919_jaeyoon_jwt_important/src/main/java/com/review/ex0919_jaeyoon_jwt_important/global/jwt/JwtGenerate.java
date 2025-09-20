package com.review.ex0919_jaeyoon_jwt_important.global.jwt;

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
public class JwtGenerate {

    @Value("${custom.jwt.code}")
    private String code;

    private SecretKey secretKey;

    // application-secret.yml에 등록되어 있는 code를 사용해서 Key를 만들자.
    public SecretKey getSecretKey() {
        if (secretKey == null) {

            // 아래는 암호화를 하는것이 아니라 데이터를 문자열로 변환시킨 것 뿐이다.
            // String encodingCode = Base64.getEncoder().encodeToString(code.getBytes());
            byte[] encodingCode = Base64.getEncoder().encode(code.getBytes());

            // 변환시킨 문자열로 JWT 서명을 위한 키를 만드는 것.
            secretKey = Keys.hmacShaKeyFor(encodingCode);
        }
        return secretKey;
    }

    // 토큰을 만들자*****
    // 토큰을 만들건데 Map에 인자로 Id값이 인자로 들어올 거다. 그 아이디로
    public String GenerateToken(Map<String, Object> map, int second) {
        Long now = new Date().getTime();

        Date expireDate = new Date(now + (1000L * second));

        // builder 안에 Pjy라는 이름으로 만료날짜를 넣자.
        JwtBuilder builder = Jwts.builder().subject("PJY").expiration(expireDate);

        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();
        
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            
            // 빌더 안에 키값과 value 값을 또 적는다.
            builder.claim(key,value);

        } // 반복문 마지막.

        String jwt = builder.signWith(getSecretKey()).compact();
        return jwt;
    }

    // 토큰 유효한지 검사
    public boolean isOK(String token){
        boolean isok= true;
        try{ 
        Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch(Exception e){
            isok=false;
        }
        return isok;
    }

    // 토큰을 통해 정보(claims)를 넘겨주자
    public Map<String,Object> getClaims(String token){
        return Jwts.parser().verifyWith(getSecretKey()).build()
        .parseSignedClaims(token).getPayload();
    }


}
