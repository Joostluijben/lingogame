package joost.luijben.infrastructure.output.round.jpa;

import joost.luijben.domain.RoundRepository;
import joost.luijben.domain.*;
import joost.luijben.infrastructure.output.game.jpa.GameEntity;
import joost.luijben.infrastructure.output.game.jpa.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoundRepositoryJpaAdapter implements RoundRepository {
    private final joost.luijben.infrastructure.output.round.jpa.RoundRepository roundRepository;
    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;

    public RoundRepositoryJpaAdapter(joost.luijben.infrastructure.output.round.jpa.RoundRepository roundRepository, ModelMapper modelMapper, GameRepository gameRepository) {
        this.roundRepository = roundRepository;
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
    }

    @Override
    public Round getRoundById(Integer roundId) {
        return modelMapper.map(roundRepository.getOne(roundId), Round.class);
    }

    @Override
    public RoundDto createRoundForGame(Integer gameId, Set<Word> words, Integer wordLength) {
        GameEntity gameEntity = gameRepository.getOne(gameId);
        Round round = new Round(words, wordLength);
        RoundEntity roundEntity = modelMapper.map(round, RoundEntity.class);
        roundEntity.setGame(gameEntity);
        RoundEntity saveRoundEntity = roundRepository.save(roundEntity);
        round.setId(saveRoundEntity.getId());
        return RoundToRoundDtoMapper.map(round);
    }
}
