package fr.backend.tp_backend.repository;

import fr.backend.tp_backend.models.Favorite;
import fr.backend.tp_backend.models.Plant;
import fr.backend.tp_backend.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer > {
}
