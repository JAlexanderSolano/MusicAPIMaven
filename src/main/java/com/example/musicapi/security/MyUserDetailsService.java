/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.musicapi.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí puedes cargar desde base de datos, pero por ahora lo hacemos manual:
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.builder()
                .username("admin")
                .password("{noop}admin123") // {noop} = sin codificación
                .roles("USER")
                .build();
    } 
}
