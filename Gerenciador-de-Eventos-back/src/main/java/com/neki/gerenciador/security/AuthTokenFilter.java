package com.neki.gerenciador.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.neki.gerenciador.security.jwt.JwtUtil;
import com.neki.gerenciador.security.services.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Pega o token JWT do header Authorization
            String jwt = parseJwt(request);

            // Se o token for válido
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Extrai o 'username' do token
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                // Carrega os detalhes do usuário
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Cria o token de autenticação
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                // Configura os detalhes da requisição
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define a autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Não foi possível realizar a autenticação do usuário: {}", e);
        }

        // Continua a execução da requisição
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        // Extrai o token JWT do header Authorization
        String headerAuth = request.getHeader("Authorization");

        // Verifica se o header tem o formato "Bearer <token>"
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // Remove "Bearer " do início
        }

        return null;
    }
}
