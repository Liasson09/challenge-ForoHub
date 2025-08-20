package com.milagros.challenge_ForoHub.controller;

import com.milagros.challenge_ForoHub.domain.usuario.DatosAutenticacionUsuario;
import com.milagros.challenge_ForoHub.domain.usuario.DatosRegistroUsuario;
import com.milagros.challenge_ForoHub.domain.usuario.Usuario;
import com.milagros.challenge_ForoHub.domain.usuario.UsuarioRepository;
import com.milagros.challenge_ForoHub.infra.security.DatosJWTToken;
import com.milagros.challenge_ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
        var authentication = authenticationManager.authenticate(authToken);

        var jwtToken = tokenService.generarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByLogin(datos.login())) {
            return ResponseEntity.badRequest().body("El login ya está en uso");
        }

        if (usuarioRepository.existsByEmail(datos.email())) {
            return ResponseEntity.badRequest().body("El email ya está en uso");
        }

        // Crear nuevo usuario
        Usuario usuario = new Usuario(datos);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}
