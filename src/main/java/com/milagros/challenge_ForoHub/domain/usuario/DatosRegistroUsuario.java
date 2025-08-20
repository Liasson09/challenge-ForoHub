package com.milagros.challenge_ForoHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank(message = "Login es obligatorio")
        @Size(min = 3, max = 20, message = "Login debe tener entre 3 y 20 caracteres")
        String login,

        @NotBlank(message = "Clave es obligatoria")
        @Size(min = 5, message = "La clave debe tener al menos 6 caracteres")
        String clave,

        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Email es obligatorio")
        @Email(message = "Email debe tener un formato válido")
        String email
) {}