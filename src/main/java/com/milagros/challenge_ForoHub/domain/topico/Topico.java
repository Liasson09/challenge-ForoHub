package com.milagros.challenge_ForoHub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private String autor;
    private String curso;

    public Topico() {
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.ABIERTO;
    }

    public Topico(DatosRegistroTopico datos, String autorNombre) {
        this();
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autorNombre;
        this.curso = datos.curso();
    }

    public void actualizarInformacion(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.status() != null) {
            this.status = datos.status();
        }
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public StatusTopico getStatus() { return status; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }
}
