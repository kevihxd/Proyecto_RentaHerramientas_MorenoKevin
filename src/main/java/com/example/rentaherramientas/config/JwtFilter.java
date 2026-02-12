package com.example.rentaherramientas.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        // DIAGNOSTICO: Imprimir si llega el header
        if (authHeader != null) {
            System.out.println("🔎 Filtro JWT: Header recibido -> " + authHeader);
        } else {
            // Solo imprimir esto si no es una ruta pública para no llenar de basura la consola
            if(!request.getRequestURI().contains("/auth")) {
                System.out.println("⚠️ Filtro JWT: No llegó header Authorization en " + request.getRequestURI());
            }
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String username = jwtUtil.extractUsername(token);
                // Intentamos leer el ROL
                String role = jwtUtil.extractClaim(token, claims -> claims.get("role", String.class));

                System.out.println("✅ Filtro JWT: Usuario -> " + username + " | Rol detectado -> " + role);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // IMPORTANTE: Aseguramos que el rol no sea nulo antes de asignarlo
                    if (role == null) {
                        System.out.println("❌ ERROR: El rol vino NULO dentro del token.");
                    } else {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                username, null, Collections.singletonList(new SimpleGrantedAuthority(role))); // <--- Aquí asignamos el permiso

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("🔓 Autorización concedida a Spring Security con rol: " + role);
                    }
                }
            } catch (Exception e) {
                System.out.println("⛔ Error procesando JWT: " + e.getMessage());
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}