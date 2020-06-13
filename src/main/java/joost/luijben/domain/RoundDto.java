package joost.luijben.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoundDto {
    private Integer id;
    private char letter;
    private Integer wordLength;
    private Integer score;
    private boolean won;
    private Turn turn;

    public RoundDto(Integer id, char letter, Integer wordLength, Integer score, boolean won, List<Turn> turns) {
        this.id = id;
        this.letter = letter;
        this.wordLength = wordLength;
        this.score = score;
        this.won = won;
        if (turns.size() > 0) {
            turn = turns.get(turns.size() - 1);
        }
    }
}
