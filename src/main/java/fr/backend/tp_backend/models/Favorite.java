package fr.backend.tp_backend.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Favorite {

    public int plant_id;

    public int user_id;
    public String user_uuid;
}
