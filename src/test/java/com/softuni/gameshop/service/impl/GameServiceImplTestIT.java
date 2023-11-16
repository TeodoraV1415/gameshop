package com.softuni.gameshop.service.impl;

import com.softuni.gameshop.model.DTO.game.AddGameDTO;
import com.softuni.gameshop.model.DTO.game.EditGameDTO;
import com.softuni.gameshop.model.DTO.game.GameCardDTO;
import com.softuni.gameshop.model.DTO.game.GameDetailsDTO;
import com.softuni.gameshop.model.Game;
import com.softuni.gameshop.model.Genre;
import com.softuni.gameshop.model.enums.GenreNamesEnum;
import com.softuni.gameshop.repository.GameRepository;
import com.softuni.gameshop.repository.GenreRepository;
import com.softuni.gameshop.repository.ReviewRepository;
import com.softuni.gameshop.service.GameService;
import com.softuni.gameshop.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTestIT {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddGame() {
        // Arrange
        AddGameDTO addGameDTO = new AddGameDTO();
        addGameDTO.setTitle("Test Game");
        addGameDTO.setGenre(GenreNamesEnum.ADVENTURE);
        addGameDTO.setDescription("Test description");
        addGameDTO.setReleaseYear(2022);
        addGameDTO.setPrice(BigDecimal.valueOf(49.99));
        addGameDTO.setImageURL("test-image-url");
        addGameDTO.setVideoURL("test-video-url");

        Game game = new Game();
        game.setTitle("Test Game");

        when(modelMapper.map(addGameDTO, Game.class)).thenReturn(game);
        when(genreRepository.findByName(GenreNamesEnum.ADVENTURE)).thenReturn(Optional.of(new Genre()));
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        // Act
        Long gameId = gameService.addGame(addGameDTO);

        // Assert
        assertEquals(game.getId(), gameId);
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    void testDeleteGame() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game().setId(gameId);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // Act
        gameService.deleteGame(gameId);

        // Assert
        assertTrue(game.isDeleted());
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    void testGetGameDetails() {
        // Arrange
        Long gameId = 1L;
        Game game = new Game().setId(gameId);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // Act
        GameDetailsDTO gameDetailsDTO = gameService.getGameDetails(game.getId());
        gameDetailsDTO.setId(game.getId());

        // Assert
        assertNotNull(gameDetailsDTO);
        assertEquals(gameId, gameDetailsDTO.getId());
    }

    @Test
    void testGetGameDetailsNotFound() {
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> gameService.getGameDetails(gameId));
    }

    @Test
    void testDeleteGameNotFound() {
        Long gameId = 1L;
        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> gameService.deleteGame(gameId));
    }


}
