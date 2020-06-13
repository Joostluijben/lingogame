package joost.luijben.application;

import joost.luijben.domain.Game;
import joost.luijben.domain.GameRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class GameProcessor {
    private final GameRepository gameRepositoryJpa;
    private final GameRepository gameRepositoryMemory;

    public GameProcessor(@Qualifier("gameJpaAdapter") GameRepository gameRepositoryJpa, @Qualifier("gameMemoryAdapter") GameRepository gameRepositoryMemory) {
        this.gameRepositoryJpa = gameRepositoryJpa;
        this.gameRepositoryMemory = gameRepositoryMemory;
    }

    public Game createGame(String playerName) {
        Game game = gameRepositoryJpa.createGameForPlayer(playerName);
        gameRepositoryMemory.addGame(game);
        return game;
    }

    public Game stopGame(String playerName, Integer gameId) {
        gameRepositoryMemory.calculateScoreForGame(gameId);
        Game game = gameRepositoryMemory.getGameByGameId(gameId);
        gameRepositoryMemory.removeGame(gameId);
        return gameRepositoryJpa.saveGame(playerName, game);
    }
}
