package com.lucas.bandeira.games.games.service;

import com.lucas.bandeira.games.games.controller.Controller;
import com.lucas.bandeira.games.games.entity.Games;
import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import com.lucas.bandeira.games.games.repository.GamesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GameService {

    @Autowired
    private GamesRepository repository;

    public ResponseEntity<Object> create(GamesRecordDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var games = new Games(dto);
        repository.save(games);
        return ResponseEntity.status(HttpStatus.CREATED).body(games);
    }
    public ResponseEntity<Games> findById(Long id) {
        Optional<Games> gamesOptional = repository.findById(id);
        if (gamesOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(gamesOptional.get());

        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Games>> findAll() {
        List<Games> gamesList = repository.findAll();
        if (gamesList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        for (Games games : gamesList) {

            games.add(linkTo(methodOn(Controller.class).findById(games.getId())).withSelfRel());

        }
        return ResponseEntity.ok().body(gamesList);

    }
    public ResponseEntity<String> deleteById(Long id) {
        Optional<Games> gamesOptional = repository.findById(id);
        if (gamesOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("The game has been deleted");
        } else
            throw new RuntimeException("Id: " + id + " does not exists!");
    }
    public ResponseEntity<Games> updateById( Long id,GamesRecordDto dto) {
        Optional<Games> gamesOptional = repository.findById(id);
        if (gamesOptional.isPresent()) {
            Games saveGame = gamesOptional.get();
            BeanUtils.copyProperties(dto,saveGame);
            return ResponseEntity.ok().body(repository.save(saveGame));
        } else
            throw new RuntimeException("Id: " + id + " does not exists!");

    }
}
