package joost.luijben.application;

import joost.luijben.domain.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoundProcessor {
    private final WordInput wordInput;
    private final RoundRepository roundRepository;
    private final GameRepository gameRepositoryMemory;

    public RoundProcessor(WordInput wordInput, RoundRepository roundRepository, @Qualifier("gameMemoryAdapter") GameRepository gameRepositoryMemory) {
        this.wordInput = wordInput;
        this.roundRepository = roundRepository;
        this.gameRepositoryMemory = gameRepositoryMemory;
    }

    public RoundDto startRound(Integer gameId, Integer wordLength) {
        RoundDto roundDto = roundRepository.createRoundForGame(gameId, wordInput.load(wordLength), wordLength);
        Round round = roundRepository.getRoundById(roundDto.getId());
        round.setWordLength(wordLength);
        gameRepositoryMemory.addRoundToGame(round, gameId);
        return roundDto;
    }
}
