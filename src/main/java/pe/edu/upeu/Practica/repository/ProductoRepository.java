package pe.edu.upeu.Practica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.Practica.entity.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Override
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = "categoria")
    org.springframework.data.domain.Page<Producto> findAll(org.springframework.data.domain.Pageable pageable);

    @EntityGraph(attributePaths = "categoria")
    @Query("""
            select p from Producto p join p.categoria c
            where (:categoriaId is null or c.id = :categoriaId)
              and (:vendible = false or (p.estado = true and p.stock > 0))
              and (:q is null or locate(lower(:q), lower(p.nombre)) > 0
                   or locate(lower(:q), lower(c.nombre)) > 0)
            """)
    Page<Producto> buscar(Long categoriaId, boolean vendible, String q, Pageable pageable);

    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);

    List<Producto> findByCategoriaId(Long categoriaId);
    boolean existsByCategoriaId(Long categoriaId);
}
