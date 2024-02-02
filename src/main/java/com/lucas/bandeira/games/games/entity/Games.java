package com.lucas.bandeira.games.games.entity;

import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.Year;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Games extends RepresentationModel<Games> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Year release_year;
    private String shortDescription;
    private String image;

    public Games (GamesRecordDto gamesRecordDto){
        this.setName(gamesRecordDto.name());
        this.setRelease_year(gamesRecordDto.release_year());
        this.setShortDescription(gamesRecordDto.shortDescription());
        this.setImage(gamesRecordDto.image());
    }
}
