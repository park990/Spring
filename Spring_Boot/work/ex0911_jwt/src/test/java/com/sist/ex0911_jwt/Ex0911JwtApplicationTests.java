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

	@Test
	@DisplayName("동일한 SecretKey인지 확인")
	void sameSecretKey() {
		SecretKey sKey1 = jwtProvider.getSecretKey();
		SecretKey sKey2 = jwtProvider.getSecretKey();

		assertThat(sKey1 == sKey2).isTrue();
	}

	@Test
	@DisplayName("유효한 토큰인지? 확인")
	void tokenValidTest() {
		Map<String, Object> map= new HashMap<>();
		
		map.put("mid","admin");
		map.put("name","이도");
		map.put("mphone","010-1234-5678");

		// 토큰 생성 :-1 을넣어 바로 만료되는 토큰을 받는다.
		String token = jwtProvider.genToken(map,60*60);
		System.out.println("TOKEN::"+token);

		assertThat(jwtProvider.verify(token)).isTrue();
	}

	@Test
	@DisplayName("토큰으로 claims 정보 확인")
	void tokenClaimsTest(){
		Map<String, Object> map= new HashMap<>();
		
		map.put("mid","admin");
		map.put("name","이도");
		map.put("mphone","010-1234-5678");

		// 토큰 생성 :-1 을넣어 바로 만료되는 토큰을 받는다.
		String token = jwtProvider.genToken(map,60*60);
		System.out.println("TOKEN::"+token);

		// 유효한 토큰인지 검증을 받는다.
		assertThat(jwtProvider.verify(token)).isTrue();

		// 토큰에 저장되어어 있는 정보를 받는다.
		// 위 Map과 동일한 것인지 확인
		Map<String, Object> cMap = jwtProvider.getClaims(token);
		System.out.println("cMap::"+cMap);
		System.out.println("cMap.NAME::"+cMap.get("name"));
	}
}
