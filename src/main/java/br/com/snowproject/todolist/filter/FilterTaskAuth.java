package br.com.snowproject.todolist.filter;

import br.com.snowproject.todolist.user.IUserRepository;
import br.com.snowproject.todolist.user.UserModel;
import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    private final IUserRepository userRepository;

    public FilterTaskAuth(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!request.getServletPath().startsWith("/tasks")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            authenticateRequest(request);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            response.sendError(e.getStatusCode(), e.getMessage());
        }
    }

    private void authenticateRequest(HttpServletRequest request) throws AuthenticationException {
        String[] credentials = decodeCredentials(request);
        String username = credentials[0];
        String password = credentials[1];

        UserModel user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new AuthenticationException(HttpServletResponse.SC_UNAUTHORIZED, "Usuário ou senha inválidos");
        }

        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (!passwordVerify.verified) {
            throw new AuthenticationException(HttpServletResponse.SC_UNAUTHORIZED, "Usuário ou senha inválidos");
        }

        request.setAttribute("idUser", user.getId());
    }

    private String[] decodeCredentials(HttpServletRequest request) throws AuthenticationException {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            throw new AuthenticationException(HttpServletResponse.SC_UNAUTHORIZED,
                    "Header Authorization ausente ou malformado");
        }

        var authEncoded = authorizationHeader.substring("Basic ".length()).trim();
        byte[] authDecoded;
        try {
            authDecoded = Base64.getDecoder().decode(authEncoded);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException(HttpServletResponse.SC_BAD_REQUEST, "Token Base64 inválido");
        }

        var authString = new String(authDecoded);
        String[] credentials = authString.split(":", 2); // Limita para 2 para evitar erros com ":" na senha

        if (credentials.length != 2) {
            throw new AuthenticationException(HttpServletResponse.SC_BAD_REQUEST, "Formato de credenciais inválido");
        }

        return credentials;
    }

    private static class AuthenticationException extends Exception {
        private final int statusCode;

        public AuthenticationException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}