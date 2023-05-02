package fr.backend.tp_backend.services;

import fr.backend.tp_backend.models.Plant;
import fr.backend.tp_backend.models.User;
import fr.backend.tp_backend.models.Favorite;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@Getter
public class UserService {
    @Autowired
    private fr.backend.tp_backend.repository.UserRepository UserRepository;
    @Autowired
    private fr.backend.tp_backend.repository.PlantRepository PlantRepository;

    public @ResponseBody Iterable <User> getUser(){
        return UserRepository.findAll();
    }


    public User addFavorite(Integer plant_id, String user_uuid) {

        Plant plant = PlantRepository.findById(plant_id).orElseThrow(() -> new RuntimeException("Objet Plant introuvable"));


        User user = new User();
        if (UserRepository.findByUuid(user_uuid).isEmpty()) {
            user.setUuid(user_uuid);
            UserRepository.save(user);

            user.getPlant().add(plant);
            UserRepository.save(user);
        } else {
            user = UserRepository.findByUuid(user_uuid).get(0);
            user.getPlant().add(plant);
            UserRepository.save(user);
        }

        return user;
        // Récupérer l'objet B correspondant à l'ID bId

        // Ajouter l'objet B à la collection d'objets B de l'objet A

        // Enregistrer l'objet A mis à jour en base de données
    }

    public User delFavorite(Integer plant_id, String user_uuid){
        Plant plant = PlantRepository.findById(plant_id).orElseThrow(() -> new RuntimeException("Objet Plant introuvable"));


        User user = new User();
        if (UserRepository.findByUuid(user_uuid).size() == 0) {
            user.setUuid(user_uuid);
            UserRepository.save(user);

            user.getPlant().add(plant);
            UserRepository.save(user);
        } else {
            user = UserRepository.findByUuid(user_uuid).get(0);
            user.getPlant().remove(plant);
            UserRepository.save(user);
        }

        return user;
    }

}
