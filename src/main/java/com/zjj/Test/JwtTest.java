package com.zjj.Test;

import com.zjj.common.constant.Constants;
import com.zjj.utils.UUID.IdUtils;
import com.zjj.web.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt 分成3部分 Header Payload Signature
 * Header header典型的由两部分组成：token的类型（“JWT”）和算法名称（比如：HMAC SHA256或者RSA等等）
 * 例如:  { 'alg': "HS256", 'typ': "JWT" }
 * 然后，用Base64对这个JSON编码就得到JWT的第一部分
 * <p>
 * <p>
 * Payload JWT的第二部分是payload，它包含声明（要求）。声明是关于实体(通常是用户)和其他数据的声明。声明有三种类型: registered, public 和 private。
 * Registered claims : 这里有一组预定义的声明，它们不是强制的，但是推荐。比如：iss (issuer), exp (expiration time), sub (subject), aud (audience)等。
 * Public claims : 可以随意定义。
 * Private claims : 用于在同意使用它们的各方之间共享信息，并且不是注册的或公开的声明。
 * 例子：{
 * "sub": '1234567890',
 * "name": 'john',
 * "admin":true
 * }
 * <p>
 * Signature
 * 为了得到签名部分，你必须有编码过的header、编码过的payload、一个秘钥，签名算法是header中指定的那个，然对它们签名即可。
 */
public class JwtTest {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    private static final String secret = "abcdefghijklmnopqrstuvwxyz";

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    public static String createJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) throws IOException {
        final BASE64Encoder encoder = new BASE64Encoder();
        final BASE64Decoder decoder = new BASE64Decoder();

        Map<String, Object> claims = new HashMap<>();
        String token = IdUtils.fastUUID();
        claims.put(Constants.LOGIN_USER_KEY, token);

        String jwt = createJwt(claims);
        Claims claims1 = parseToken(jwt);
        // 生成jwt
        System.out.println(jwt);
        // 解析jwt
        System.out.println(claims1);
        // 获取jwt的字节
        System.out.println(new String(decoder.decodeBuffer(jwt), "UTF-8"));

        String header = "eyJhbGciOiJIUzUxMiJ9";
        String payload = "eyJsb2dpbl91c2VyX2tleSI6IjYzZTYyNmE4LTU0ZTktNDAwNi04OGJlLTM4NDk4ZWI4YmZlNiJ9";
        String signature = "V8naqHwMAiEaJ_XSO1aXu14Zpy4OEWDNtefyFGkQ3obh0RhL3Qgr8dtBZ-YbU7JNHf30ObEMp65DY_em8odx_A";
        byte[] decode = TextCodec.BASE64.decode(header + "." + payload + "." + signature);
        System.out.println(new String(decode, "UTF-8"));
        System.out.println(new String(decoder.decodeBuffer(header), "UTF-8"));
        System.out.println(new String(decoder.decodeBuffer(payload), "UTF-8"));
        System.out.println(new String(decoder.decodeBuffer(signature), "UTF-8"));
    }
}
