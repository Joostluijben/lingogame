package joost.luijben.application;

import joost.luijben.domain.GameRepository;
import joost.luijben.domain.RoundDto;
import joost.luijben.domain.RoundRepository;
import joost.luijben.domain.WordInput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TurnProcessor {
    private final GameRepository gameRepository;
    private final WordInput wordInput;
    private final RoundRepository roundRepository;

    public TurnProcessor(@Qualifier("gameMemoryAdapter") GameRepository gameRepository, WordInput wordInput, RoundRepository roundRepository) {
        this.gameRepository = gameRepository;
        this.wordInput = wordInput;
        this.roundRepository = roundRepository;
    }

    public RoundDto makeTurn(Integer gameId, Integer roundId, String inputWord) {
        return gameRepository.addTurnToRoundByGameId(gameId, roundId, inputWord, wordInput.load(roundRepository.getRoundById(roundId).getWordLength()));
    }
}
