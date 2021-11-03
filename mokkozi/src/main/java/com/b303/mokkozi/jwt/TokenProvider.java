package com.b303.mokkozi.jwt;

import com.b303.mokkozi.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

// 토큰을 생성하고, 토큰의 유효성을 검증하는 메소드가 포함되어 있다.

// TokenProvider 클래스를 Bean 객체로 등록하기 위해 @Component 어노테이션을 사용
@Component
// InitializingBean : 새롭게 Bean객체를 생성하고 해당 Bean 객체의 property를 초기화한다.
public class TokenProvider implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;

    // Constructor. 토큰의 secret key와 만료기한 값을 불러와서, TokenProvider의 클래스 변수에 저장한다.
    public TokenProvider(
            // application.properties에서 설정한 Base64 기반 secret key를 먼저 불러온다.
            @Value("${jwt.secret}") String secret,
            // application.properties에서 설정한 토큰 만료기한값을 불러온다.
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    // 주로 Bean 객체의 property가 모두 올바르게 설정되었는지를 검사하는 용도로 주로 사용된다.
    // Keys : secret key와 key쌍들을 암호화하여 생성하기 위한 유틸리티 클래스.
    public void afterPropertiesSet() throws Exception {
        // Base64로 인코딩한 문자열을 다시 디코드.
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);    // 새로운 key를 생성한다.
    }

    // Authentication 객체의 권한 정보를 이용하여 토큰을 생성하는 메소드
    // Authentication : 인증 요청에 대한 토큰을 의미하거나, 인증 메서드에 의해 인증 권한이 부여된 토큰을 의미한다.
    /* stream : Java8부터 추가된 기능으로, 기존에 자바 컬렉션이나 배열의 원소를 가공할 때, for문, foreach문으로 원소 하나씩 골라내서
    가공했다면, Stream을 이용하여 람다함수 형식으로 간결하게 개별 원소에 대한 처리가 가능해졌다.
    stream().map()은 요소들을 특정조건에 해당하는 값으로 변환해 준다. ex) 대,소문자 변형 작업
    */
    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        // 토큰의 만료기한을 설정한다.
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        // 토큰을 생성하여 return한다.
        return Jwts.builder()
                // 아래의 내용은 registered claim을 설정하는 과정이다.
                // claim은? JWT내 payload에 들어가는 데이터의 형태이다. 하나의 claim은 key-value 쌍으로 이루어져 있다.
                // claim의 종류로는 registered claim, public claim, private claim이 존재한다.
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    // 역으로, 생성된 토큰을 매개변수로 받아서 토큰에 담겨있는 권한 정보를 이용하여 Authenticaion 객체를 반환하는 메소드
    // 이 메소드는 안써도 될지도...
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User user = new User();
        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    // 토큰을 파라미터로 받아 파싱한 후, 문제가 발생하면 return false, 문제가 없으면 return true
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


}
