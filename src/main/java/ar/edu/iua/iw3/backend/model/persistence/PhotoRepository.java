package ar.edu.iua.iw3.backend.model.persistence;

import ar.edu.iua.iw3.backend.model.Hotel;
import ar.edu.iua.iw3.backend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    public List<Photo> findByHotel(Hotel hotel);
}
