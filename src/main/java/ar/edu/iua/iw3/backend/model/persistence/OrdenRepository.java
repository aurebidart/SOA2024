package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

    public Optional<Orden> findById(Long id);

    public List<Orden> findByEstado(Integer estado);
    
    @Query(value = "SELECT o FROM Orden o WHERE o.estado IN :estados AND o.fechaCargaPrevista BETWEEN :fechaInicio AND :fechaFin")
    public List<Orden> findByEstadosAndFechaCargaPrevistaBetween(List<Integer> estados, Date fechaInicio, Date fechaFin);


}