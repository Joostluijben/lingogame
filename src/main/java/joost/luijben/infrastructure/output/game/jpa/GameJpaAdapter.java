package joost.luijben.infrastructure.output.game.jpa;

import joost.luijben.domain.Game;
import joost.luijben.domain.Round;
import joost.luijben.domain.RoundDto;
import joost.luijben.domain.Word;
import joost.luijben.infrastructure.output.player.jpa.PlayerEntity;
import joost.luijben.infrastructure.output.player.jpa.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service("gameJpaAdapter")
public class GameJpaAdapter implements joost.luijben.domain.GameRepository {
    private final GameRepository gameRepository;
    private final joost.luijben.infrastructure.output.player.jpa.PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public GameJpaAdapter(GameRepository gameRepository, PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Game createGameForPlayer(String playerName) {
        PlayerEntity playerEntity = playerRepository.getByName(playerName).orElseThrow(EntityNotFoundException::new);
        GameEntity gameEntity = modelMapper.map(new Game(), GameEntity.class);
        gameEntity.setPlayer(playerEntity);
        GameEntity savedGame = gameRepository.save(gameEntity);
        return modelMapper.map(savedGame, Game.class);
    }

    @Override
    public Game saveGame(String playerName, Game game) {
        PlayerEntity playerEntity = playerRepository.getByName(playerName).orElseThrow(EntityNotFoundException::new);
        GameEntity foundGame = playerEntity.getGames().stream().filter(gameEntity -> gameEntity.getId().equals(game.getId())).findFirst().orElseThrow(EntityNotFoundException::new);
        foundGame.setScore(game.getScore());
        GameEntity gameEntity = gameRepository.save(foundGame);
        return modelMapper.map(gameEntity, Game.class);
    }

    @Override
    public Game getGameByGameId(Integer gameId) {
        return modelMapper.map(gameRepository.getOne(gameId), Game.class);
    }

    @Override
    public void removeGame(Integer gameId) {

    }

    @Override
    public void calculateScoreForGame(Integer gameId) {

    }

    @Override
    public RoundDto addTurnToRoundByGameId(Integer gameId, Integer roundId, String wordInput, Set<Word> words) {
        return null;
    }

    @Override
    public void addRoundToGame(Round round, Integer gameId) {

    }

    @Override
    public void addGame(Game game) {

    }
}
