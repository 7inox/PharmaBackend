package pe.edu.upeu.Practica.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.edu.upeu.Practica.auditoria.EventoAuditoria;

@Entity
@Table(name = "auditoria_eventos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuditoriaEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @Column(nullable = false, length = 50)
    private String usuario;
    @Column(nullable = false, length = 40)
    private String accion;
    @Column(nullable = false, length = 40)
    private String entidad;
    @Column(name = "entidad_id")
    private Long entidadId;
    @Column(length = 500)
    private String detalle;
    @Column(name = "request_id", length = 36)
    private String requestId;

    public AuditoriaEvento(EventoAuditoria evento) {
        fecha = evento.fecha();
        usuario = evento.usuario();
        accion = evento.accion();
        entidad = evento.entidad();
        entidadId = evento.entidadId();
        detalle = evento.detalle();
        requestId = evento.requestId();
    }
}
