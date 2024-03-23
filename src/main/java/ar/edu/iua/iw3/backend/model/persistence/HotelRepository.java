package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    public List<Hotel> findByName(String name);

    public List<Hotel> findByCity(String city);

    public List<Hotel> findAll();

    public Optional<Hotel> findByid(Long id);

}
