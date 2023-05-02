package fr.backend.tp_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "plant")
public class Plant {
    @Id
    @Column(name="id", columnDefinition = "int default 100")
    public int id;

    @Column(name="common_name")
    public String common_name;

    @Column(name="slug")
    public String slug;

    @Column(name="scientific_name")
    public String scientific_name;

    @Column(name="year")
    public String year;

    @Column(name="genus")
    public String genus;

    @Column(name="family")
    public String family;

    @Column(name="image_url")
    public String image_url;

    @ManyToMany(mappedBy = "plant")
    @JsonIgnore
    Set<User> favorite;

    public Plant() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
