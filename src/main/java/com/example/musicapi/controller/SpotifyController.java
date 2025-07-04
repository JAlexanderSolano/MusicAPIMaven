/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.musicapi.controller;

import com.example.musicapi.service.SpotifyApiService;
import com.example.musicapi.service.SpotifyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/spotify")
public class SpotifyController {
    @Autowired
    private SpotifyAuthService spotifyAuthService;
    
    @Autowired
    private SpotifyApiService spotifyApiService;

    @GetMapping("/token")
    public Mono<String> obtenerToken() {
        return spotifyAuthService.obtenerToken();
    }
    
    @GetMapping("/buscar-artista/{nombre}")
    public Mono<String> buscarArtista(@RequestParam String nombre) {
        return spotifyApiService.buscarArtista(nombre);
    }
}
