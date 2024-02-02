package com.lucas.bandeira.games.games.controller;

import com.lucas.bandeira.games.games.entity.Games;
import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import com.lucas.bandeira.games.games.repository.GamesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/games")
public class Controller {
    @Autowired
    private GamesRepository repository;

    @PostMapping
    public ResponseEntity<Object>create(@Valid @RequestBody GamesRecordDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var games = new Games(dto);
        repository.save(games);
        return ResponseEntity.status(HttpStatus.CREATED).body(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games>findById(@PathVariable Long id){
        Optional<Games>gamesOptional = repository.findById(id);
        if (gamesOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(gamesOptional.get());

        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Games>>findAll(){
        List<Games>gamesList = repository.findAll();
        if (gamesList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        for (Games games : gamesList){

            games.add( linkTo(methodOn(Controller.class).findById(games.getId())).withSelfRel());

        return  ResponseEntity.ok().body(gamesList);
        }
        return ResponseEntity.internalServerError().build();
    }

}
