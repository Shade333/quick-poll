package ua.com.foxminded.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ua.com.foxminded.domain.Role;
import ua.com.foxminded.domain.User;
import ua.com.foxminded.repository.UserRepository;

@Component
public class QuickPollUserDetailsService implements UserDetailsService {

    @Inject
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User with the username %s doesn't exist", username));
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole() == Role.ADMIN) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_STAFF", "ROLE_VISITOR");
        } else {
            AuthorityUtils.createAuthorityList("ROLE_" + user.getRole());
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
}