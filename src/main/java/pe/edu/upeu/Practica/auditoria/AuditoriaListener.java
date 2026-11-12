package pe.edu.upeu.Practica.auditoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import pe.edu.upeu.Practica.service.impl.AuditoriaService;

@Component
public class AuditoriaListener {
    private static final Logger LOG = LoggerFactory.getLogger(AuditoriaListener.class);
    private final AuditoriaService service;

    public AuditoriaListener(AuditoriaService service) {
        this.service = service;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void registrar(EventoAuditoria evento) {
        try {
            service.registrar(evento);
        } catch (RuntimeException error) {
            LOG.error("No se pudo auditar {} de {}", evento.accion(), evento.entidad(), error);
        }
    }
}
