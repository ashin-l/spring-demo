package com.example.springdemo.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.example.springdemo.security.constants.SecurityConstants;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JWT加密和解密的工具类
 */
// public class JwtUtil {
//     /**
//      * 加密字符串 禁泄漏
//      */
//     public static final String SECRET = SecurityConstants.JWT_SECRET_KEY;
//     public static final int JWT_ERROR_CODE_NULL = 4000; // Token不存在
//     public static final int JWT_ERROR_CODE_EXPIRE = 4001; // Token过期
//     public static final int JWT_ERROR_CODE_FAIL = 4002; // 验证不通过

//     /**
//      * 签发JWT
//      * 
//      * @param id
//      * @param subject
//      * @param ttlMillis
//      * @return String
//      */
//     public static String createJWT(String id, String subject, long ttlMillis) {
//         SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//         long nowMillis = System.currentTimeMillis();
//         Date now = new Date(nowMillis);
//         SecretKey secretKey = generalKey();
//         JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject) // 主题
//                 .setIssuer("thfw") // 签发者
//                 .setIssuedAt(now) // 签发时间
//                 .signWith(secretKey, signatureAlgorithm); // 签名算法以及密匙
//         if (ttlMillis >= 0) {
//             long expMillis = nowMillis + ttlMillis;
//             Date expDate = new Date(expMillis);
//             builder.setExpiration(expDate); // 过期时间
//         }
//         return builder.compact();
//     }

//     /**
//      * 验证JWT
//      * 
//      * @param jwtStr
//      * @return CheckResult
//      */
//     public static CheckResult validateJWT(String jwtStr) {
//         CheckResult checkResult = new CheckResult();
//         Claims claims;
//         try {
//             claims = parseJWT(jwtStr);
//             checkResult.setSuccess(true);
//             checkResult.setClaims(claims);
//         } catch (ExpiredJwtException e) {
//             checkResult.setErrCode(JWT_ERROR_CODE_EXPIRE);
//             checkResult.setSuccess(false);
//         } catch (SignatureException e) {
//             checkResult.setErrCode(JWT_ERROR_CODE_FAIL);
//             checkResult.setSuccess(false);
//         } catch (Exception e) {
//             checkResult.setErrCode(JWT_ERROR_CODE_FAIL);
//             checkResult.setSuccess(false);
//         }
//         return checkResult;
//     }

//     /**
//      * 密钥
//      * 
//      * @return
//      */
//     public static SecretKey generalKey() {
//         // byte[] encodedKey = Base64.decode(SECRET);
//         SecretKey key = new SecretKeySpec(SECRET.getBytes(), "AES");
//         return key;
//     }

//     /**
//      * 解析JWT字符串
//      * 
//      * @param jwt
//      * @return
//      * @throws Exception Claims
//      */
//     public static Claims parseJWT(String jwt) {
//         SecretKey secretKey = generalKey();
//         return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
//     }
// }

/**
 * @author lqc
 */
public class JwtUtil {

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    // private static byte[] apiKeySecretBytes =
    // DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
    private static byte[] apiKeySecretBytes = Base64.getEncoder().encode(SecurityConstants.JWT_SECRET_KEY.getBytes());
    private static SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

    public static String createToken(String username, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;

        // String token = Jwts.builder().setHeaderParam("typ",
        // SecurityConstants.TOKEN_TYPE)
        // .signWith(secretKey, SignatureAlgorithm.HS256)
        // .claim(SecurityConstants.ROLE_CLAIMS, String.join(",",
        // roles)).setIssuer("SnailClimb")
        // .setIssuedAt(new Date()).setSubject(username)
        // .setExpiration(new Date(System.currentTimeMillis() + expiration *
        // 1000)).compact();

        var token = Jwts.builder().setSubject(username).claim(SecurityConstants.ROLE_CLAIMS, roles)
                .setIssuer(SecurityConstants.ISSUER).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();
        return token;
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }

    public static String getUsernameByToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取用户所有角色
     */
    public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
        List<String> roles = (List<String>) getTokenBody(token).get(SecurityConstants.ROLE_CLAIMS);
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        // return
        // Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
