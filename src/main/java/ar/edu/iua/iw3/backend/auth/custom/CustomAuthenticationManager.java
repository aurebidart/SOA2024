package ar.edu.iua.iw3.backend.auth.custom;

import java.util.Collection;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.iua.iw3.backend.auth.IUserBusiness;
import ar.edu.iua.iw3.backend.auth.User;
import ar.edu.iua.iw3.backend.business.Exceptions.BusinessException;
import ar.edu.iua.iw3.backend.business.Exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationManager implements AuthenticationManager {

    public CustomAuthenticationManager(PasswordEncoder pEncoder, IUserBusiness userBusiness) {
        this.pEncoder = pEncoder;
        this.userBusiness = userBusiness;
    }

    private PasswordEncoder pEncoder;
    private IUserBusiness userBusiness;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = null;
        try {
            user = userBusiness.load(username);
        } catch (NotFoundException e) {
            throw new BadCredentialsException("No existe un usuario registradoc on estas credenciales");
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            throw new AuthenticationServiceException(e.getMessage());
        }
        String validation = user.validate();
        if (validation.equals(User.VALIDATION_ACCOUNT_EXPIRED))
            throw new AccountExpiredException(User.VALIDATION_ACCOUNT_EXPIRED);
        if (validation.equals(User.VALIDATION_CREDENTIALS_EXPIRED))
            throw new CredentialsExpiredException(User.VALIDATION_CREDENTIALS_EXPIRED);
        if (validation.equals(User.VALIDATION_DISABLED))
            throw new DisabledException(User.VALIDATION_DISABLED);
        if (validation.equals(User.VALIDATION_LOCKED))
            throw new LockedException(User.VALIDATION_LOCKED);

        if (!pEncoder.matches(password, user.getPassword())) 
            throw new BadCredentialsException("Password incorrecto");

        return new UsernamePasswordAuthenticationToken(user, null);
    }

    @SuppressWarnings("serial")
    public Authentication authWrap(String name, String pass) {

        return new Authentication() {
            
            @Override
            public String getName() {
                return name;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return pass;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }
        };
    }
}
