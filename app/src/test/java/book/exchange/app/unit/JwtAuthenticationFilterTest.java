package book.exchange.app.unit;

import book.exchange.app.configuration.JwtAuthenticationFilter;
import book.exchange.app.configuration.JwtAuthenticationProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

    @Mock
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    // ------------------------------------------------------
    // 1. No Authorization header
    // ------------------------------------------------------
    @Test
    void testNoAuthorizationHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtAuthenticationProvider);
    }

    // ------------------------------------------------------
    // 2. Header does not start with "Bearer "
    // ------------------------------------------------------
    @Test
    void testInvalidAuthorizationHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("InvalidToken");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(jwtAuthenticationProvider);
    }

    // ------------------------------------------------------
    // 3. Valid header but JWT provider returns null
    // (username null OR token invalid)
    // ------------------------------------------------------
    @Test
    void testInvalidToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer badtoken");
        when(jwtAuthenticationProvider.authenticate("badtoken", request)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(jwtAuthenticationProvider).authenticate("badtoken", request);
        verify(filterChain).doFilter(request, response);
    }

    // ------------------------------------------------------
    // 4. Token valid but authentication is already set
    // ------------------------------------------------------
    @Test
    void testAlreadyAuthenticated() throws Exception {
        Authentication existingAuth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(existingAuth);

        when(request.getHeader("Authorization")).thenReturn("Bearer token123");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // jwtAuthenticationProvider should NOT be called
        verifyNoInteractions(jwtAuthenticationProvider);
        verify(filterChain).doFilter(request, response);
    }

    // ------------------------------------------------------
    // 5. Full success: provider returns Authentication
    // ------------------------------------------------------
    @Test
    void testSuccessfulAuthentication() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer goodtoken");

        Authentication mockAuth = mock(Authentication.class);
        when(jwtAuthenticationProvider.authenticate("goodtoken", request))
                .thenReturn(mockAuth);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assert SecurityContextHolder.getContext().getAuthentication() == mockAuth;

        verify(jwtAuthenticationProvider).authenticate("goodtoken", request);
        verify(filterChain).doFilter(request, response);
    }

    // ------------------------------------------------------
    // 6. Provider returns null (invalid token AFTER username)
    // ------------------------------------------------------
    @Test
    void testTokenValidButAuthenticationFails() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer badvalidtoken");

        when(jwtAuthenticationProvider.authenticate("badvalidtoken", request))
                .thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(jwtAuthenticationProvider).authenticate("badvalidtoken", request);
        verify(filterChain).doFilter(request, response);
    }
}
