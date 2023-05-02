package fr.backend.tp_backend.controllers;

import fr.backend.tp_backend.models.Plant;
import fr.backend.tp_backend.models.User;
import fr.backend.tp_backend.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@RestController("plant")
@CrossOrigin(origins = "http://localhost:4200/")
public class PlantControllers {

    @Autowired
    private PlantService PlantService;
    private RestTemplate restTemplate;

    @Autowired
    private fr.backend.tp_backend.repository.UserRepository UserRepository;

    public PlantControllers(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/plant/all")
    public @ResponseBody Iterable <Plant> helloWorld(){
        return PlantService.getPlant();
    }


    @GetMapping("/search")
    public @ResponseBody Iterable <Plant> search(@RequestParam(value="data") String search) throws IOException {
        String apiUrl = "https://trefle.io/api/v1/plants/search?token=0mrTih49QQMX8udL9EMWNh42vfuOy2S4pQuSMATtYTM&q="+ search;
        String myData = restTemplate.getForObject(apiUrl, String.class);
        return PlantService.searchPlant(myData);
    }

    @GetMapping("/favorites")
    public @ResponseBody Iterable <Plant> favorites(@RequestParam(value="data") String user_uuid) throws IOException {
        User user = UserRepository.findByUuid(user_uuid).get(0);
        return user.getPlant();
    }


    @CrossOrigin
    @PostMapping("/plant")
    public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) {
        Plant savedPlant = PlantService.addPlant(plant.getId(), plant.getCommon_name(), plant.getSlug(), plant.getScientific_name(), plant.getYear(), plant.getGenus(), plant.getFamily(), plant.getImage_url());
        return ResponseEntity.ok(savedPlant);
    }

    @DeleteMapping("/plant")
    public ResponseEntity removePlant(@RequestParam(value="id") Integer id){
        PlantService.removePlant(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/plant")
    @PatchMapping("/plant")
    public ResponseEntity updatePlantState(
            @RequestParam(value="id") Integer id,
            @RequestParam(value="common_name") String common_name,
            @RequestParam(value="slug") String slug,
            @RequestParam(value="scientific_name") String scientific_name,
            @RequestParam(value="year") String year,
            @RequestParam(value="genus") String genus,
            @RequestParam(value="family") String family,
            @RequestParam(value="image_url") String image_url
    ){
        PlantService.updatePlant( id, common_name, slug, scientific_name, year, genus, family, image_url);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/erreur")
    public ResponseEntity error(){
        return ResponseEntity.internalServerError().build();
    }




}
