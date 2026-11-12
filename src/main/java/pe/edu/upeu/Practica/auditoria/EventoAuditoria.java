package pe.edu.upeu.Practica.auditoria;

import java.time.LocalDateTime;
import org.slf4j.MDC;
import pe.edu.upeu.Practica.config.AuditoriaConfig;

public record EventoAuditoria(LocalDateTime fecha, String usuario, String accion,
                              String entidad, Long entidadId, String detalle, String requestId) {
    public static EventoAuditoria negocio(String accion, String entidad, Long id, String detalle) {
        return new EventoAuditoria(LocalDateTime.now(), AuditoriaConfig.usuarioActual(),
                accion, entidad, id, detalle, MDC.get("requestId"));
    }

    public static EventoAuditoria login(String accion, String usuario) {
        return new EventoAuditoria(LocalDateTime.now(), usuario, accion, "Usuario",
                null, null, MDC.get("requestId"));
    }
}
