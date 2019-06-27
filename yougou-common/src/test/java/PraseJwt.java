import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @Package: test
 * @ClassName: PraseJwt
 * @Description: Java类作用
 * @Author: 式神
 * @CreateDate: 2019/5/24 23:09
 */
public class PraseJwt {
    public static void main(String[] args) {
        try {
            Claims claims=Jwts.parser().setSigningKey("yougou")
                    .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1NjE1NjM1NDQsImV4cCI6MTU2MTU2MzYwNCwicm9sZSI6ImFkbWluIn0.379Z36ouGVjFDUU0a8p3WJiDQG3WlJsuhzMfZlvR2vc")
                    .getBody();
            System.out.println("用户id："+ claims.getId());
            System.out.println("用户名："+ claims.getSubject());
            System.out.println("登录时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
            System.out.println("过期时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
            System.out.println("用户角色："+claims.get("role"));
        } catch (ExpiredJwtException e) {
            System.out.println("Token已过期,请重新获取");

        }

    }
}
