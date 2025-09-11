package com.sist.ex0911_jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sist.ex0911_jwt.jwt.JwtProvider;

import io.jsonwebtoken.security.Keys;

// 단언 테스트를 static import를 하자.
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@ActiveProfiles("test")
@SpringBootTest
public class Ex0911JwtApplicationTests {

	@Value("${custom.jwt.secretKey}")
	private String secretCode;

	@Test
	@DisplayName("SECRETKEY CODE CHECK")
	void loadSecretKeyCode() {
		// 단언: assertJ
		assertThat(secretCode).isNotNull();
	}

	@Test
	@DisplayName("암호화알고리즘으로 시크릿 Key 암호화")
	void genBase64() {
		String encoding = Base64.getEncoder().encodeToString(secretCode.getBytes());
		SecretKey sKey = Keys.hmacShaKeyFor(encoding.getBytes());
		assertThat(sKey).isNotNull();
	}

	@Autowired
	private JwtProvider jwtProvider;

	@Test
	@DisplayName("accessToken발급")
	void tokenTest() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", "admin");
		claims.put("uname", "어두일미");
		claims.put("upwd", "1234");
		claims.put("uemail", "admin@korea.com");

		String accessToken = jwtProvider.genToken(claims, 60 * 60 * 3);
		System.out.println("ACCESS-TOCKEN:" + accessToken);
		assertThat(accessToken).isNotNull();
	}
}
