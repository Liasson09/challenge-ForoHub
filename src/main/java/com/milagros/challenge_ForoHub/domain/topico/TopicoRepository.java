package com.milagros.challenge_ForoHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCurso(String curso);

    List<Topico> findByAutor(String autor);

    Page<Topico> findByAutor(String autor, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.titulo LIKE %:palabra%")
    List<Topico> findByTituloContaining(@Param("palabra") String palabra);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findAllByOrderByFechaCreacionDesc(Pageable pageable);
}
