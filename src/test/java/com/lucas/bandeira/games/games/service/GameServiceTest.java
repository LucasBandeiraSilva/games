package com.lucas.bandeira.games.games.service;

import com.lucas.bandeira.games.games.entity.Games;
import com.lucas.bandeira.games.games.record.dto.GamesRecordDto;
import com.lucas.bandeira.games.games.repository.GamesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindingResult;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GamesRepository gamesRepository;
    @InjectMocks
    private GameService gameService;

    Games game;


    @BeforeEach
    void setUp(){
          game = new Games(20L,"Ghost",2020,"samurai game","path");

    }
    @Test
    @DisplayName("Should create a game successfully")
    void create() {

        GamesRecordDto dto = new GamesRecordDto("Spider-man", 2018, "Best spider game", "path");


        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        when(gamesRepository.save(any())).thenAnswer(invocation -> {
            Games game = invocation.getArgument(0);
            game.setId(1L);
            return game;
        });


        Games createdGame = gameService.create(dto, bindingResult);


        assertNotNull(createdGame.getId());
        assertEquals("Spider-man", createdGame.getName());
        assertEquals(2018, createdGame.getRelease_year());
        assertEquals("Best spider game", createdGame.getShortDescription());
        assertEquals("path", createdGame.getImage());
        verify(gamesRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Should return the game with the id")
    void findById() {
        when(gamesRepository.findById(game.getId())).thenReturn(Optional.of(game));

        Games foundGame = gameService.findById(game.getId());

        assertEquals(game.getId(),foundGame.getId());
        assertEquals(game.getName(), foundGame.getName());
        assertEquals(game.getRelease_year(), foundGame.getRelease_year());
        assertEquals(game.getShortDescription(), foundGame.getShortDescription());
        assertEquals(game.getImage(), foundGame.getImage());

        verify(gamesRepository, times(1)).findById(game.getId());


    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {

        when(gamesRepository.findById(game.getId())).thenReturn(Optional.of(game));
        gameService.deleteById(game.getId());
        verify(gamesRepository, times(1)).deleteById(game.getId());
    }

    @Test
    void updateById() {

    }
}