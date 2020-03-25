package se.typelias.dndbackend.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import se.typelias.dndbackend.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class MyUserDetails implements UserDetails {

    @Autowired
    UserRepository userRepository;

    private String userName;
    private String password;
    private String role;

    public MyUserDetails(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*Optional<User> u = userRepository.findByUserName(this.userName);
        if (u.isPresent()) {
            return Arrays.asList(new SimpleGrantedAuthority(u.get().getRole()));
        }*/
        return Arrays.asList(new SimpleGrantedAuthority(this.role));


    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
