/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.musicapi.repository;

import com.example.musicapi.model.ListaReproduccion;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ListaReproduccionRepository extends JpaRepository<ListaReproduccion, String> {
}
