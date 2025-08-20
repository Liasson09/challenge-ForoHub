package com.milagros.challenge_ForoHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank(message = "Login es obligatorio")
        String login,

        @NotBlank(message = "Clave es obligatoria")
        String clave
) {}
