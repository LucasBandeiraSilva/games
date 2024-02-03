package com.lucas.bandeira.games.games.controller;

import com.lucas.bandeira.games.games.entity.Games;
import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import com.lucas.bandeira.games.games.repository.GamesRepository;
import com.lucas.bandeira.games.games.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
    private GameService gameService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody GamesRecordDto dto, BindingResult bindingResult) {
        return  gameService.create(dto,bindingResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Games> findById(@PathVariable Long id) {
       return gameService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Games>> findAll() {
        return gameService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return gameService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Games> updateById(@PathVariable Long id, @RequestBody GamesRecordDto dto) {
       return gameService.updateById(id,dto);
    }
}