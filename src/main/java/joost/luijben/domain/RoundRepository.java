package joost.luijben.domain;

import java.util.Set;

public interface RoundRepository {
    Round getRoundById(Integer roundId);
    RoundDto createRoundForGame(Integer gameId, Set<Word> words, Integer wordLength);
}
