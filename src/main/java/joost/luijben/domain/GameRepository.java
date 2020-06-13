package joost.luijben.domain;

import java.util.Set;

public interface GameRepository {
    Game createGameForPlayer(String playerName);
    Game saveGame(String playerName, Game game);
    Game getGameByGameId(Integer gameId);
    void removeGame(Integer gameId);
    void calculateScoreForGame(Integer gameId);
    RoundDto addTurnToRoundByGameId(Integer gameId, Integer roundId, String wordInput, Set<Word> words);
    void addRoundToGame(Round round, Integer gameId);
    void addGame(Game game);
}
