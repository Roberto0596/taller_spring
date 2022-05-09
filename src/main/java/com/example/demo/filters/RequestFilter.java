package com.example.demo.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Utils.Helper;

@Component
public class RequestFilter extends OncePerRequestFilter {
	private static final Logger log = LoggerFactory.getLogger(Helper.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		long begin = System.currentTimeMillis();
		filterChain.doFilter(request, response);
		long end = System.currentTimeMillis();
		
		log.info("Tiempo total de ejecucion de peticion: {}", (end - begin));
	}
}
