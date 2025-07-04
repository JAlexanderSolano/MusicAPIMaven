package com.example.musicapi.controller;

import com.example.musicapi.model.Cancion;
import com.example.musicapi.model.ListaReproduccion;
import com.example.musicapi.repository.ListaReproduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/lists")
public class ListaReproduccionController {

    @Autowired
    private ListaReproduccionRepository repo;

    // Crear nueva lista
    @PostMapping
    public ResponseEntity<?> crearLista(@RequestBody ListaReproduccion lista) {
        if (lista.getNombre() == null || lista.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body("El nombre de la lista no puede ser vacío");
        }
        repo.save(lista);
        return ResponseEntity.created(URI.create("/lists/" + lista.getNombre())).body(lista);
    }

    // Obtener todas las listas
    @GetMapping
    public List<ListaReproduccion> obtenerTodas() {
        return repo.findAll();
    }

    // Obtener una lista por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerUna(@PathVariable String nombre) {
        Optional<ListaReproduccion> listaOpt = repo.findById(nombre);
        if (listaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Lista '" + nombre + "' no encontrada"));
        }

        return ResponseEntity.ok(listaOpt.get());
    }

    // Eliminar una lista por nombre validando que no contenga canciones
    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminarLista(@PathVariable String nombre) {
        Optional<ListaReproduccion> listaOpt = repo.findById(nombre);

        if (listaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Lista '" + nombre + "' no encontrada"));
        }

        ListaReproduccion lista = listaOpt.get();

        if (!lista.getCanciones().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", "No se puede eliminar la lista porque contiene canciones"));
        }

        repo.delete(lista);
        return ResponseEntity.ok(Map.of("mensaje", "Lista '" + nombre + "' eliminada correctamente"));
    }

    // Metodo para agregar cancion a una lista
    @PostMapping("/{nombreLista}/canciones")
    public ResponseEntity<?> agregarCancionALista(
            @PathVariable String nombreLista,
            @RequestBody Cancion nuevaCancion
    ) {
        Optional<ListaReproduccion> listaOpt = repo.findById(nombreLista);
        if (listaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Lista no encontrada"));
        }

        ListaReproduccion lista = listaOpt.get();
        lista.getCanciones().add(nuevaCancion);
        repo.save(lista);

        return ResponseEntity.ok(Map.of(
                "mensaje", "Canción agregada correctamente a la lista " + nombreLista
        ));
    }

    // Metodo para eliminar una cancion por nombre 
    @DeleteMapping("/{nombreLista}/canciones/{idCancion}")
    public ResponseEntity<?> eliminarCancionDeLista(
            @PathVariable String nombreLista,
            @PathVariable Long idCancion
    ) {
        Optional<ListaReproduccion> listaOpt = repo.findById(nombreLista);

        if (listaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Lista '" + nombreLista + "' no encontrada"));
        }

        ListaReproduccion lista = listaOpt.get();

        boolean fueRemovida = lista.getCanciones().removeIf(c -> c.getId().equals(idCancion));

        if (!fueRemovida) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Canción con ID " + idCancion + " no encontrada en la lista"));
        }

        repo.save(lista);

        return ResponseEntity.ok(Map.of("mensaje", "Canción eliminada correctamente de la lista"));
    }
}
