package com.milagros.challenge_ForoHub.controller;

import com.milagros.challenge_ForoHub.domain.topico.DatosActualizarTopico;
import com.milagros.challenge_ForoHub.domain.topico.DatosRegistroTopico;
import com.milagros.challenge_ForoHub.domain.topico.DatosRespuestaTopico;
import com.milagros.challenge_ForoHub.domain.topico.TopicoService;
import com.milagros.challenge_ForoHub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datos,
            @AuthenticationPrincipal Usuario usuario,
            UriComponentsBuilder uriBuilder) {

        DatosRespuestaTopico topico = topicoService.registrarTopico(datos, usuario.getNombre());
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.id()).toUri();
        return ResponseEntity.created(url).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable pageable) {
        Page<DatosRespuestaTopico> page = topicoService.listarTopicos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopico(@PathVariable Long id) {
        DatosRespuestaTopico topico = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos,
            @AuthenticationPrincipal Usuario usuario) {

        DatosRespuestaTopico topicoActualizado = topicoService.actualizarTopico(id, datos, usuario.getNombre());
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario usuario) {
        topicoService.eliminarTopico(id, usuario.getNombre());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mis-topicos")
    public ResponseEntity<Page<DatosRespuestaTopico>> listarMisTopicos(
            @AuthenticationPrincipal Usuario usuario,
            @PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable pageable) {
        Page<DatosRespuestaTopico> page = topicoService.listarTopicosPorAutor(usuario.getNombre(), pageable);
        return ResponseEntity.ok(page);
    }
}