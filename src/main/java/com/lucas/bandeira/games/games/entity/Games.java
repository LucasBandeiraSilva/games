package com.lucas.bandeira.games.games.entity;

import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Games extends RepresentationModel<Games> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer release_year;
    private String shortDescription;
    private String image;

    public Games (GamesRecordDto gamesRecordDto){
        this.setName(gamesRecordDto.name());
        this.setRelease_year(gamesRecordDto.release_year());
        this.setShortDescription(gamesRecordDto.shortDescription());
        this.setImage(gamesRecordDto.image());
    }
}
