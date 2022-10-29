package com.howabout.there.token;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.howabout.there.sign.service.SignInService;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtutil;
	@Autowired
	private SignInService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Header에서 Authorization 를 키로 값을 가져옴
		String authorizationHeader = request.getHeader("Authorization");
		
		String token = null;
		String userName = null;
		//header가 null이 아니거나  Bearer로 시작한다면 >> Bearer이 타입을 나타낸다
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")) {
			//substring = n번째 인덱스를 포함하여 그 뒤 문자를 가져옴
			token = authorizationHeader.substring(7);
			System.out.println("TOKEN : 필터안  토큰 : "+token);
			userName = jwtutil.extractUsername(token);
			System.out.println("userName CHECK : " + userName);
		}
		//SecurityContextHolder.getContext().getAuthentication()==현재 사용자 정보
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			System.out.println("****************** Filter contenthold null ");
			
			//사용자 id로 UserVo를 찾아 UserDetails에 맞춰서 변환
			UserDetails userDetails = service.loadUserByUsername(userName);
			System.out.println("USERDETAILS ? ? ? ? ?    "+userDetails.toString());
			System.out.println("FUCKING CHECK NAME  "+ userDetails.getUsername());
			//사용자 이름일치 AND 토큰 유효시간 확인 
			if (jwtutil.validateToken(token, userDetails)) {
				//TRUE
				System.out.println("11");
				UsernamePasswordAuthenticationToken userNamePwAuthToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				System.out.println("22");
				userNamePwAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				System.out.println("33");
				SecurityContextHolder.getContext().setAuthentication(userNamePwAuthToken);
				System.out.println("44");
			}
		}
		filterChain.doFilter(request, response);
	}

}
