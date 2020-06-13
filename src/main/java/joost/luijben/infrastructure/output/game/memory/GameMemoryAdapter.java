package joost.luijben.infrastructure.output.game.memory;

import joost.luijben.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service("gameMemoryAdapter")
public class GameMemoryAdapter implements GameRepository {
    private final Set<Game> games;

    public GameMemoryAdapter() {
        this.games = new HashSet<>();
    }

    @Override
    public void addGame(Game game) {
        games.add(game);
    }

    @Override
    public void addRoundToGame(Round round, Integer gameId) {
        games.stream().filter(game -> game.getId().equals(gameId)).findFirst().orElseThrow(EntityNotFoundException::new).getRounds().add(round);
    }

    @Override
    public RoundDto addTurnToRoundByGameId(Integer gameId, Integer roundId, String wordInput, Set<Word> words) {
        Round foundRound = games.stream().filter(game -> game.getId().equals(gameId)).findFirst().orElseThrow(EntityNotFoundException::new)
                .getRounds().stream().filter(round -> round.getId().equals(roundId)).findFirst().orElseThrow(EntityNotFoundException::new);
        return foundRound.makeTurn(wordInput, words);
    }

    @Override
    public void calculateScoreForGame(Integer gameId) {
       this.games.stream().filter(game -> game.getId().equals(gameId)).findFirst().orElseThrow(EntityNotFoundException::new).calculateScoreForGame();
    }

    @Override
    public void removeGame(Integer gameId) {
        games.remove(games.stream().filter(game -> game.getId().equals(gameId)).findFirst().orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public Game getGameByGameId(Integer gameId) {
        return this.games.stream().filter(game -> game.getId().equals(gameId)).findFirst().orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Game createGameForPlayer(String playerName) {
        return null;
    }

    @Override
    public Game saveGame(String playerName, Game game) {
        return null;
    }
}
