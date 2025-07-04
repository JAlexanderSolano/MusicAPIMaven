
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.musicapi.security;

import java.util.Collections;
import org.springframework.context.annotation.Primary;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Solo se permite el usuario "admin"
        if ("admin".equals(username)) {
            return new User(
                "admin",
                "{noop}admin123",  // "{noop}" indica que no se encripta la contraseña
                Collections.emptyList() // No se asignan roles
            );
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}