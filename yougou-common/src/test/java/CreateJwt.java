import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Package: test
 * @ClassName: CreateJwt
 * @Description: Java类作用
 * @Author: 式神
 * @CreateDate: 2019/5/24 23:00
 */
public class CreateJwt {
    public static void main(String[] args) {
       JwtBuilder jwtBuilder= Jwts.builder()
                .setId("666")
                .setSubject("小马")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"yougou")
                .setExpiration(new Date(System.currentTimeMillis() +60000))
                .claim("role","admin");
        System.out.println(jwtBuilder.compact());
    }
}
