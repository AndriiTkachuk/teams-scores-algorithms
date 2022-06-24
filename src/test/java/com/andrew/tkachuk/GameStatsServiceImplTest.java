package com.andrew.tkachuk;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStatsServiceImplTest {
    private final Collection<GameRecord> gameRecords = List.of(
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("Boston Bruins", "Marchan", 2, 1),
            new GameRecord("Boston Bruins", "Pastrniak", 5, 1)
    );

    private final Collection<GameRecord> gameRecords2 = List.of(
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("Boston Bruins", "Zibanezhad", 5, 1),
            new GameRecord("Boston Bruins", "Pastrniak", 6, 1)
    );

    private final Collection<GameRecord> gameRecords3 = List.of(
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("Boston Bruins", "Marchan", 5, 1),
            new GameRecord("Boston Bruins", "Pastrniak", 5, 2)
    );

    @Test
    void winnerTest() {
        GameStatsServiceImpl gameStatsService = new GameStatsServiceImpl();
        assertEquals("Boston Bruins", gameStatsService.winner(gameRecords));
    }

    @Test
    void mvp() {
        GameStatsServiceImpl gameStatsService = new GameStatsServiceImpl();
        assertEquals("Zibanezhad", gameStatsService.mvp(gameRecords2));
    }
}