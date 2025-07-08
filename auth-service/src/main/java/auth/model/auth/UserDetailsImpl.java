package auth.model.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Data


public class UserDetailsImpl implements UserDetails{
    private  long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>authorities;

    public UserDetailsImpl(long id, String password,
                           Collection<? extends GrantedAuthority> authorities, String emailAddress) {
        this.id = id;
        this.password = password;
        this.authorities = authorities;
        this.email = emailAddress;

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
