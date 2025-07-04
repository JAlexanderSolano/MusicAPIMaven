/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.musicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class SpotifyApiService {
    @Autowired
    private SpotifyAuthService authService;

    private final WebClient webClient = WebClient.create("https://api.spotify.com/v1");

    public Mono<String> buscarArtista(String nombreArtista) {
        return authService.obtenerToken().flatMap(token ->
            webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/search")
                    .queryParam("q", nombreArtista)
                    .queryParam("type", "artist")
                    .build())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(String.class)
        );
    }
}
