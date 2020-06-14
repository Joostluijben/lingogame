package joost.luijben.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoundTest {
    private static final Set<Word> words = new HashSet<>(Set.of(new Word("flor"), new Word("flora"), new Word("floor"), new Word("floraa"), new Word("floraaa"), new Word("floraaaa")));
    @Test
    public void checkFirstLetter() {
        Round round = new Round(words, null);
        round.setWord("flora");
        RoundDto roundDto = round.makeTurn("flora", words);
        Assert.assertEquals(roundDto.getLetter(), round.getWord().charAt(0));
    }

    @Test
    public void returnCorrectWordIfAlreadyWon() {
        Round round = new Round(words, 5);
        round.setWon(true);
        round.setWord("flora");
        RoundDto roundDto = round.makeTurn("flora", words);
        Assert.assertArrayEquals(roundDto.getTurn().getFeedbacks().toArray(), List.of(
                new Feedback('f', Status.Correct),
                new Feedback('l', Status.Correct),
                new Feedback('o', Status.Correct),
                new Feedback('r', Status.Correct),
                new Feedback('a', Status.Correct)
        ).toArray());
    }

    @Test
    public void returnCorrectWordIfMaxTurnAmountIsExceeded() {
        Round round = new Round(words, null);
        round.setWord("flora");
        round.makeTurn("floor", words);
        round.makeTurn("floor", words);
        round.makeTurn("floor", words);
        round.makeTurn("floor", words);
        round.makeTurn("floor", words);
        RoundDto roundDto = round.makeTurn("floor", words);
        Assert.assertArrayEquals(roundDto.getTurn().getFeedbacks().toArray(), List.of(
                new Feedback('f', Status.Correct),
                new Feedback('l', Status.Correct),
                new Feedback('o', Status.Correct),
                new Feedback('r', Status.Correct),
                new Feedback('a', Status.Correct)
        ).toArray());
    }
}
