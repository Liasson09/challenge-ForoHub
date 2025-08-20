package com.milagros.challenge_ForoHub.domain.topico;

import com.milagros.challenge_ForoHub.infra.exceptions.TopicoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos, String autorNombre) {
        // Validar que no exista un tópico duplicado
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico topico = new Topico(datos, autorNombre);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    public Page<DatosRespuestaTopico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAllByOrderByFechaCreacionDesc(pageable)
                .map(DatosRespuestaTopico::new);
    }

    public Page<DatosRespuestaTopico> listarTopicosPorAutor(String autor, Pageable pageable) {
        return topicoRepository.findByAutor(autor, pageable)
                .map(DatosRespuestaTopico::new);
    }

    public DatosRespuestaTopico obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico con ID " + id + " no encontrado"));
        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datos, String autorNombre) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico con ID " + id + " no encontrado"));

        // Verificar que el usuario sea el autor del tópico
        if (!topico.getAutor().equals(autorNombre)) {
            throw new IllegalArgumentException("Solo el autor puede actualizar el tópico");
        }

        topico.actualizarInformacion(datos);
        return new DatosRespuestaTopico(topico);
    }

    @Transactional
    public void eliminarTopico(Long id, String autorNombre) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico con ID " + id + " no encontrado"));

        // Verificar que el usuario sea el autor del tópico
        if (!topico.getAutor().equals(autorNombre)) {
            throw new IllegalArgumentException("Solo el autor puede eliminar el tópico");
        }

        topicoRepository.deleteById(id);
    }
}
