package fr.backend.tp_backend.controllers;

import fr.backend.tp_backend.models.Favorite;
import fr.backend.tp_backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserControllers {
    @Autowired
    private fr.backend.tp_backend.services.UserService UserService;

    @GetMapping("/user/all")
    public @ResponseBody Iterable <User> helloWorld(){
        return UserService.getUser();
    }



    @CrossOrigin
    @PostMapping("/favorite")
    public ResponseEntity<User> addFavorite(@RequestBody Favorite favorite) {
        User addFavorite = UserService.addFavorite(favorite.getPlant_id(), favorite.getUser_uuid());
        return ResponseEntity.ok(addFavorite);
    }

    @CrossOrigin
    @PostMapping("/delfavorite")
    public ResponseEntity<User> delFavorite(@RequestBody Favorite favorite) {
        User delFavorite = UserService.delFavorite(favorite.getPlant_id(), favorite.getUser_uuid());
        return ResponseEntity.ok(delFavorite);
    }

}
