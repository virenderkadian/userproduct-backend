package com.sample.app01.config;

import java.io.IOException;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sample.app01.service.UserDetailsServiceImpl;


public class JwtTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			 String token = getTokenFromRequest(request);
			System.out.println("Token-- " + token);
			boolean validate=jwtTokenUtil.validateJwtToken(token);
			System.out.println("validate--" + validate);
			if (token != null && jwtTokenUtil.validateJwtToken(token)) {
				String email = jwtTokenUtil.getEmailFromJwtToken(token);
				System.out.println("Email--JwtTokenFilter-- " + email);
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				System.out.println("Authorities--JwtTokenFilter-- " + userDetails);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null,userDetails.getAuthorities());
				System.out.println(authentication);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			//logger.error("Cannot set user authentication: {}", e);
			throw new RuntimeException("Cannot set user authentication" + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			// remove "Bearer "
			return token.substring(7, token.length());
		}
		return null;
	}
}
