package pe.edu.upeu.Practica.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.Practica.dto.VentaRequestDTO;
import pe.edu.upeu.Practica.dto.VentaResponseDTO;
import pe.edu.upeu.Practica.enums.EstadoVenta;
import pe.edu.upeu.Practica.service.service.VentaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {
    private static final Logger LOG = LoggerFactory.getLogger(VentaController.class);

    private final VentaService ventaService;

    public VentaController(
            VentaService ventaService) {

        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> registrar(
            @Valid
            @RequestBody VentaRequestDTO request) {

        VentaResponseDTO response =
                ventaService.registrar(request);
        LOG.info("Venta registrada | id={} | items={}", response.getId(), request.getDetalles().size());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ventaService.buscar(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listar() {

        return ResponseEntity.ok(
                ventaService.listar()
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscar(

            @RequestParam(required = false)
            Long clienteId,

            @RequestParam(required = false)
            EstadoVenta estado,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate desde,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate hasta,

            @RequestParam(required = false, defaultValue = "fecha")
            String ordenarPor,

            @RequestParam(required = false, defaultValue = "desc")
            String direccion,
            @RequestParam(required = false) Integer pagina,
            @RequestParam(defaultValue = "10") int tamanio) {

        if (pagina == null) {
            return ResponseEntity.ok(ventaService.buscar(clienteId, estado, desde, hasta, ordenarPor, direccion));
        }
        return ResponseEntity.ok(ventaService.buscarPagina(clienteId, estado, desde, hasta,
                ordenarPor, direccion, pagina, tamanio));
    }
}
