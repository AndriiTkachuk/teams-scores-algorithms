package com.andrew.tkachuk;

import java.util.*;
import java.util.stream.Collectors;

public class GameStatsServiceImpl implements GameStatsService {

//     new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
//            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
//            new GameRecord("Boston Bruins", "Zibanezhad", 5, 1),
//            new GameRecord("Boston Bruins", "Pastrniak", 6, 1)
    @Override
    public String mvp(Collection<GameRecord> gameRecords) {

        Map<String, Integer> playersByScores = getPlayersByScoresMap(gameRecords);

        if (hasTheWinner(playersByScores))
            return getWinner(playersByScores);
        else {
            Map<String, Integer> playersWithSameScore = getPlayersWithSameScore(playersByScores);
            Map<String, Integer> playersByPasses = getPlayersByPasses(gameRecords);
            getWinnerByPasses(playersWithSameScore, playersByPasses);
        }

        return null;
    }

    private Map<String, Integer> getPlayersByScoresMap(Collection<GameRecord> gameRecords) {
        Map<String, Integer> playersByScores = new HashMap<>();

        return gameRecords.stream()
                .map(gameRecord -> {
                    String currentPlayerName = gameRecord.player;
                    if (playersByScores.containsKey(currentPlayerName)) {
                        int sumScores = playersByScores.get(currentPlayerName) + gameRecord.scores;
                        playersByScores.put(currentPlayerName, sumScores);
                    } else {
                        playersByScores.put(gameRecord.player, gameRecord.scores);
                    }
                    return playersByScores;

                }).toList()
                .get(0);
    }

    private Map<String, Integer> getPlayersByPasses(Collection<GameRecord> gameRecords) {
        Map<String, Integer> playersByPasses = new HashMap<>();

        return gameRecords.stream()
                .map(gameRecord -> {
                    String currentPlayerName = gameRecord.player;
                    if (playersByPasses.containsKey(currentPlayerName)) {
                        int sumScores = playersByPasses.get(currentPlayerName) + gameRecord.passes;
                        playersByPasses.put(currentPlayerName, sumScores);
                    } else {
                        playersByPasses.put(gameRecord.player, gameRecord.passes);
                    }
                    return playersByPasses;

                }).toList()
                .get(0);
    }

    private String getWinnerByPasses(Map<String, Integer> playersWithSameScore, Map<String, Integer> playersByPasses) {
        List<String> players = playersWithSameScore.keySet().stream().toList();

        return playersByPasses.entrySet().stream().filter(stringIntegerEntry -> players.contains(stringIntegerEntry.getKey()))
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(1)
                .findAny()
                .map(Map.Entry::getKey).orElse(null);
    }

    private Map<String, Integer> getPlayersWithSameScore(Map<String, Integer> playersByScores) {
        return playersByScores.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private String getWinner(Map<String, Integer> playersByScores) {
        return playersByScores.entrySet().stream().max((o1, o2) -> o2.getValue().compareTo(o1.getValue())).get().getKey();
    }

    private boolean hasTheWinner(Map<String, Integer> playersByScores) {
        return playersByScores.values().stream().max(Integer::compareTo).isPresent();
    }


//     new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
//            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
//            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
//            new GameRecord("Boston Bruins", "Marchan", 2, 1),
//            new GameRecord("Boston Bruins", "Pastrniak", 5, 1)

    @Override
    public String winner(Collection<GameRecord> gameRecords) {


//        Map<String, Integer> teamByScores = sortedTeams(gameRecords);
//
//        Map.Entry<String, Integer> stringIntegerEntry = findMaxScoresTeam(teamByScores);
//
//        if (stringIntegerEntry != null) {
//            return stringIntegerEntry.getKey();
//        }
        Map<String, Integer> teamByScores = new HashMap<>();

        return gameRecords.stream()
                .map(gameRecord -> {
                    String currentTeamName = gameRecord.team;
                    if (teamByScores.containsKey(currentTeamName)) {
                        int sumScores = teamByScores.get(currentTeamName) + gameRecord.scores;
                        teamByScores.put(currentTeamName, sumScores);
                    } else {
                        teamByScores.put(gameRecord.team, gameRecord.scores);
                    }
                    return teamByScores;

                }).toList()
                .get(0).entrySet().stream()
                .sorted((o1, o2) -> o2.getValue()
                        .compareTo(o1.getValue()))
                .limit(1)
                .findAny()
                .map(Map.Entry::getKey).orElse(null);
    }

//    private Map<String, Integer> sortedTeams(Collection<GameRecord> gameRecords) {
//        Map<String, Integer> teamByScores = new HashMap<>();
//        gameRecords.forEach(gameRecord -> {
//            String currentTeamName = gameRecord.team;
//            if (teamByScores.containsKey(currentTeamName)) {
//                int sumScores = teamByScores.get(currentTeamName) + gameRecord.scores;
//                teamByScores.put(currentTeamName, sumScores);
//            } else {
//                teamByScores.put(gameRecord.team, gameRecord.scores);
//            }
//        });
//        return teamByScores;
//    }
//
//    private Map.Entry<String, Integer> findMaxScoresTeam(Map<String, Integer> teamByScores) {
//        return teamByScores.entrySet().stream()
//                .sorted((o1, o2) -> o2.getValue()
//                        .compareTo(o1.getValue())).limit(1).findAny().orElse(null);
//    }

}
