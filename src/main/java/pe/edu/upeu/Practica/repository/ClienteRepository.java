package pe.edu.upeu.Practica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.Practica.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("""
            select c from Cliente c
            where c.estado = true
              and (locate(lower(:q), lower(concat(c.nombres, ' ', c.apellidos))) > 0
                   or locate(lower(:q), lower(concat(c.apellidos, ' ', c.nombres))) > 0
                   or locate(:q, c.dni) > 0)
            """)
    Page<Cliente> buscarActivos(String q, Pageable pageable);

    boolean existsByDni(String dni);
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByDniAndIdNot(String dni, Long id);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
