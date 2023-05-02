package fr.backend.tp_backend.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.backend.tp_backend.models.Plant;
import fr.backend.tp_backend.models.User;
import fr.backend.tp_backend.repository.PlantRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Getter
public class PlantService {

    @Autowired
    private PlantRepository PlantRepository;

    public @ResponseBody Iterable <Plant> getPlant(){
        return PlantRepository.findAll();
    }

    public @ResponseBody Iterable <Plant> searchPlant(String search) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(search);

        Iterable <Plant> plantIterable = null;

        List<Plant> plantList = new ArrayList<Plant>();

        JsonNode arrayNode = jsonNode.get("data");
        JsonNode elementNode;

        for (int i=0; i<arrayNode.size();i++) {
            elementNode = arrayNode.get(i);
            Plant plant = new Plant();
            plant.setId(elementNode.get("id").asInt());
            plant.setCommon_name(elementNode.get("common_name").asText());
            plant.setSlug(elementNode.get("slug").asText());
            plant.setScientific_name(elementNode.get("scientific_name").asText());
            plant.setYear(elementNode.get("year").asText());
            plant.setGenus(elementNode.get("genus").asText());
            plant.setFamily(elementNode.get("family").asText());
            plant.setImage_url(elementNode.get("image_url").asText());
            PlantRepository.save(plant);
            plantList.add(plant);
        }
        return plantList;
    }

    public Plant addPlant(Integer id, String common_name, String slug, String scientific_name, String year, String genus, String family, String image_url){
        Plant plant = new Plant();
        plant.setId(id);
        plant.setCommon_name(common_name);
        plant.setSlug(slug);
        plant.setScientific_name(scientific_name);
        plant.setYear(year);
        plant.setGenus(genus);
        plant.setFamily(family);
        plant.setImage_url(image_url);
        PlantRepository.save(plant);
        return plant;
    }

    public void removePlant(int id){
        PlantRepository.deleteById(id);
    }
    public void updatePlant(Integer id, String common_name, String slug, String scientific_name, String year, String genus, String family, String image_url) {
        Plant plant = new Plant();
        plant.setId(id);
        if(common_name != null){
            plant.setCommon_name(common_name);
        }
        if(slug != null){
            plant.setSlug(slug);
        }
        if(scientific_name != null){
            plant.setScientific_name(scientific_name);;
        }
        if(year != null){
            plant.setYear(year);
        }
        if(genus != null){
            plant.setGenus(genus);
        }
        if(family != null){
            plant.setFamily(family);
        }
        if(image_url != null){
            plant.setImage_url(image_url);
        }
        PlantRepository.save(plant);
    }


}
