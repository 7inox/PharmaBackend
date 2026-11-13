package pe.edu.upeu.Practica.service.impl;

import java.time.LocalDate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.Practica.auditoria.EventoAuditoria;
import pe.edu.upeu.Practica.dto.AuditoriaEventoDTO;
import pe.edu.upeu.Practica.dto.PaginaResponseDTO;
import pe.edu.upeu.Practica.entity.AuditoriaEvento;
import pe.edu.upeu.Practica.exception.ReglaNegocioException;
import pe.edu.upeu.Practica.repository.AuditoriaRepository;

@Service
public class AuditoriaService {
    private final AuditoriaRepository repository;

    public AuditoriaService(AuditoriaRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrar(EventoAuditoria evento) {
        repository.save(new AuditoriaEvento(evento));
    }

    @Transactional(readOnly = true)
    public PaginaResponseDTO<AuditoriaEventoDTO> buscar(String usuario, String accion,
            LocalDate desde, LocalDate hasta, int pagina, int tamanio) {
        if (pagina < 0 || tamanio < 1 || tamanio > 100)
            throw new ReglaNegocioException("Parámetros de paginación inválidos.");
        if (desde != null && hasta != null && desde.isAfter(hasta))
            throw new ReglaNegocioException("Rango de fechas inválido.");
        var pageable = PageRequest.of(pagina, tamanio, Sort.by(Sort.Direction.DESC, "fecha", "id"));
        return PaginaResponseDTO.de(repository.buscar(limpiar(usuario), limpiar(accion),
                desde == null ? null : desde.atStartOfDay(),
                hasta == null ? null : hasta.plusDays(1).atStartOfDay(), pageable)
                .map(AuditoriaEventoDTO::de));
    }

    private static String limpiar(String valor) {
        return valor == null || valor.isBlank() ? null : valor.trim();
    }
}
