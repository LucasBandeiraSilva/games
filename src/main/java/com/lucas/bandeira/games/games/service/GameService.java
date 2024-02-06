package com.lucas.bandeira.games.games.service;

import com.lucas.bandeira.games.games.controller.Controller;
import com.lucas.bandeira.games.games.entity.Games;
import com.lucas.bandeira.games.games.exception.GameCreationDataInvalidException;
import com.lucas.bandeira.games.games.exception.GameNotFoundException;
import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import com.lucas.bandeira.games.games.repository.GamesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GameService {

    @Autowired
    private GamesRepository repository;

    public Games create(GamesRecordDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GameCreationDataInvalidException();
        }
        var games = new Games(dto);
       return repository.save(games);
    }
    public Games findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new GameNotFoundException("There is no game with the given id: " + id));
    }

    public List<Games> findAll() {
        List<Games> gamesList = repository.findAll();
        if (!gamesList.isEmpty()) {
            for (Games games : gamesList) {
                games.add(linkTo(methodOn(Controller.class).findById(games.getId())).withSelfRel());
            }
        }

        return gamesList;

    }
    public void deleteById(Long id) {
        Optional<Games> gamesOptional = repository.findById(id);
        if (gamesOptional.isPresent()) {
            repository.deleteById(id);
        } else
            throw new GameNotFoundException("Id: " + id + " does not exists for removal");
    }
    public ResponseEntity<Games> updateById( Long id,GamesRecordDto dto) {
        Optional<Games> gamesOptional = repository.findById(id);
        if (gamesOptional.isPresent()) {
            Games saveGame = gamesOptional.get();
            BeanUtils.copyProperties(dto,saveGame);
            return ResponseEntity.ok().body(repository.save(saveGame));
        } else
            throw new GameNotFoundException ("Id: " + id + " does not exists!");

    }
}
