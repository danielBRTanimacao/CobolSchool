package CobolSchool.config.auth;

import CobolSchool.entities.UserEntity;
import CobolSchool.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        var token =  this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            UserEntity user = userRepository.findByUsername(login).orElseThrow(
                    () -> new EntityNotFoundException("User Not Found")
            );
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) return authHeader.replace("Bearer ", "");
        return null;
    }
}