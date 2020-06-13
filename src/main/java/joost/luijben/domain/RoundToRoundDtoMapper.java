package joost.luijben.domain;

public class RoundToRoundDtoMapper {
    public static RoundDto map(Round round) {
        return new RoundDto(
                round.getId(),
                round.getWord().charAt(0),
                round.getWordLength(),
                round.getScore(),
                round.isWon(),
                round.getTurns());
    }
}
