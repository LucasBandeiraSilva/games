package com.lucas.bandeira.games.games.controller;

import com.lucas.bandeira.games.games.entity.Games;
import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import com.lucas.bandeira.games.games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class Controller {
    @Autowired
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Games> create(@Valid @RequestBody GamesRecordDto dto, BindingResult bindingResult) {
        Games games =  gameService.create(dto,bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> findById(@PathVariable Long id) {
        Games game = gameService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(game);
    }

    @GetMapping
    public ResponseEntity<List<Games>> findAll() {
        List <Games> gamesList = gameService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(gamesList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
         gameService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Games> updateById(@PathVariable Long id, @RequestBody GamesRecordDto dto) {
       return gameService.updateById(id,dto);
    }
}