package joost.luijben.domain;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CheckWordServiceTest {
    private static final String fifeLetterToGuessWord = "preek";
    private static final String sixLetterToGuessWord = "prater";
    private static final String sevenLetterToGuessWord = "florist";
    private static Set<Word> words;

    @BeforeAll
    static void initialize() {
        int wordLength = 5;
        String requestUrl = System.getProperty("LINGOWORDS_URL") + "/words";
        requestUrl += "?wordLength=" + wordLength;
        List<Word> words = RestAssured.get(requestUrl)
                .then().extract().body().jsonPath().getList(".", Word.class);
        CheckWordServiceTest.words = new HashSet<>(words);
    }

    private static Stream<Arguments> provideValuesAndResultVifeLetterWord() {
        return Stream.of(
                Arguments.of("preek", fifeLetterToGuessWord, true),
                Arguments.of("treek", fifeLetterToGuessWord, false),
                Arguments.of("Preek", fifeLetterToGuessWord, false),
                Arguments.of("pree", fifeLetterToGuessWord, false),
                Arguments.of("pr!ek", fifeLetterToGuessWord, false),
                Arguments.of("", fifeLetterToGuessWord, false),
                Arguments.of(null, fifeLetterToGuessWord, false)
        );
    }

    @DisplayName("Test the validity of a fife letter word")
    @ParameterizedTest
    @MethodSource("provideValuesAndResultVifeLetterWord")
    public void isWordValid(String guessedWord, String toGuessWord, boolean expectedResult) {
        // TODO check words with six and seven letters
        boolean result = CheckWordService.isWordValid(guessedWord, toGuessWord, words);
        assertEquals(expectedResult, result);
    }

    @Test
    public void renderFifeLetterWordInvalid() {
        List<Feedback> fifeLetterFeedbacks = CheckWordService.renderWordInvalid(fifeLetterToGuessWord);
        assertArrayEquals(fifeLetterFeedbacks.toArray(), giveFeedBackArrayWithSameStatus(fifeLetterToGuessWord, Status.Invalid).toArray());
    }

    @Test
    public void renderSixLetterWordInvalid() {
        List<Feedback> sixLetterFeedbacks = CheckWordService.renderWordInvalid(sixLetterToGuessWord);
        assertArrayEquals(sixLetterFeedbacks.toArray(), giveFeedBackArrayWithSameStatus(sixLetterToGuessWord, Status.Invalid).toArray());
    }

    @Test
    public void renderSevenLetterWordInvalid() {
        List<Feedback> sevenLetterFeedbacks = CheckWordService.renderWordInvalid(sevenLetterToGuessWord);
        assertArrayEquals(sevenLetterFeedbacks.toArray(), giveFeedBackArrayWithSameStatus(sevenLetterToGuessWord, Status.Invalid).toArray());
    }

    @Test
    public void isFifeLetterWordCorrect() {
        List<Feedback> fifeLetterFeedbacks = CheckWordService.calculateFeedback(fifeLetterToGuessWord, fifeLetterToGuessWord);
        assertArrayEquals(fifeLetterFeedbacks.toArray(), giveFeedBackArrayWithSameStatus(fifeLetterToGuessWord, Status.Correct).toArray());
    }

    @Test
    public void isSixLetterWordCorrect() {
        List<Feedback> sixLetterFeedbacks = CheckWordService.calculateFeedback(sixLetterToGuessWord, sixLetterToGuessWord);
        assertArrayEquals(sixLetterFeedbacks.toArray(), giveFeedBackArrayWithSameStatus(sixLetterToGuessWord, Status.Correct).toArray());
    }

    @Test
    public void isSevenLetterWordCorrect() {
        List<Feedback> sevenLetterFeedbacks = CheckWordService.calculateFeedback(sevenLetterToGuessWord, sevenLetterToGuessWord);
        assertArrayEquals(sevenLetterFeedbacks.toArray(), giveFeedBackArrayWithSameStatus(sevenLetterToGuessWord, Status.Correct).toArray());
    }

    private List<Feedback> giveFeedBackArrayWithSameStatus(String guessedWord, Status status) {
        List<Feedback> expectedFeebacks = new ArrayList<>();
        for (int i = 0; i < guessedWord.length(); i++) {
            char letter = guessedWord.charAt(i);
            expectedFeebacks.add(new Feedback(letter, status));
        }
        return expectedFeebacks;
    }

    private static Set<Arguments> provideGuessWordGuessedWordAndResultArray() {
        return Set.of(
                Arguments.of("lingi", "lingo", List.of(
                        new Feedback('l', Status.Correct), new Feedback('i', Status.Correct),
                        new Feedback('n', Status.Correct), new Feedback('g', Status.Correct),
                        new Feedback('i', Status.Absent))
                ),
                Arguments.of("faria", "fraai", List.of(
                        new Feedback('f', Status.Correct), new Feedback('a', Status.Present),
                        new Feedback('r', Status.Present), new Feedback('i', Status.Present),
                        new Feedback('a', Status.Present))
                ),
                Arguments.of("lpele", "lepel", List.of(
                        new Feedback('l', Status.Correct),
                        new Feedback('p', Status.Present),
                        new Feedback('e', Status.Present),
                        new Feedback('l', Status.Present),
                        new Feedback('e', Status.Present)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideGuessWordGuessedWordAndResultArray")
    public void testCalculateFeedBack(String guessedWord, String toGuessWord, List<Feedback> resultArray) {
        assertArrayEquals(resultArray.toArray(), CheckWordService.calculateFeedback(guessedWord, toGuessWord).toArray());
    }
}
