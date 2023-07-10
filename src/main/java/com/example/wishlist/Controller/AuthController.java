package com.example.wishlist.Controller;

import com.example.wishlist.Dto.JwtRequest;
import com.example.wishlist.Dto.JwtResponse;
import com.example.wishlist.Service.UserService;
import com.example.wishlist.Utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> getToken(@RequestBody JwtRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getUsername(), request.getPassword()));
        UserDetails user=userService.loadUserByUsername(request.getUsername());
        String token=jwtTokenUtils.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));

    }
}
