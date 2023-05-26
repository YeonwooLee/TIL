import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTest {
	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
		String token = 
				Jwts.builder()
				.setHeaderParam("alg", "HS256")
				.setHeaderParam("typ", "JWT") //헤더완료
				.claim("userId", "ssafy")
				.setExpiration(new Date(System.currentTimeMillis()+3000)) //페이로드 완료
				.signWith(SignatureAlgorithm.HS256, "SSAFIT".getBytes("UTF-8")) //서명완료
				.compact();
		
		System.out.println(token);
		
		System.out.println(new String(Base64.getDecoder().decode(token.split("\\.")[1])));
		
		//위에서 설정한 유효기간이 3초이므로 3초 뒤에 다시한번 검사를 해보면 오류가 뜨는걸 확인해보자...
		Thread.sleep(4000);
		
		//유효성 검사
		Jws<Claims> claims = Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token);
		System.out.println(claims);
		
	}
}
