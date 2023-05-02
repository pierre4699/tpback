package fr.backend.tp_backend.repository;

import fr.backend.tp_backend.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer > {

    @Query(value="SELECT * FROM User u JOIN Favorite f on f.user_id = u.id WHERE f.plant_id = :plantId")
    List<User> findAllById(@Param("plantId") Integer plantId);

    List<User> findByUuid(String uuid);

}
