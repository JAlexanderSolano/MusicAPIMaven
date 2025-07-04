/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.musicapi.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private static final Map<String, String> USERS = new HashMap<>();

    static {
        USERS.put("admin", "admin123"); // usuario: admin / contraseña: admin123
        USERS.put("user", "user123");   // usuario: user / contraseña: user123
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = USERS.get(username);

        if (password == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.builder()
                .username(username)
                .password("{noop}" + password) // {noop} evita cifrado para pruebas
                .roles("USER")
                .build();
    }
    
}
