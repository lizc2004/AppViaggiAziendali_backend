package noemicoppotelli.appviaggiaziendali_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import noemicoppotelli.appviaggiaziendali_backend.exceptions.UnauthorizedException;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final JwtTools jwtTools;

    public TokenFilter(JwtTools jwtTools) {
        this.jwtTools = jwtTools;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Inserire il token nell'authorization header nel formato Bearer");
        }

        String token = authHeader.replace("Bearer ", "");
        jwtTools.verify(token);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match("/auth/**", request.getServletPath());
    }
}
