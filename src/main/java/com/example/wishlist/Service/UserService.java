package com.example.wishlist.Service;

import com.example.wishlist.Model.User;
import com.example.wishlist.Repository.RoleRepository;
import com.example.wishlist.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user=findByUsername(username).orElseThrow(()->new UsernameNotFoundException(
                String.format("User %s not found", username)
        ));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role->new SimpleGrantedAuthority(role.getRole()))
                        .collect(Collectors.toList()))
                .build();
    }
}
