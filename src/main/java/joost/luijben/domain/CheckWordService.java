package joost.luijben.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CheckWordService {

    public static boolean isWordValid(String guessedWord, String toGuessWord, Set<Word> words) {
        boolean isValid = false;
        if (
                guessedWord != null &&
                        toGuessWord.length() == guessedWord.length() &&
                        toGuessWord.charAt(0) == guessedWord.charAt(0) &&
                        Character.isLowerCase(guessedWord.charAt(0)) &&
                        !Pattern.matches("\\p{Punct}", guessedWord) &&
                        words.stream().anyMatch(word -> word.getValue().equals(guessedWord))) {
            isValid = true;
        }
        return isValid;
    }

    public static List<Feedback> renderWordInvalid(String guessedWord) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (int i = 0; i < guessedWord.length(); i++) {
            char letter = guessedWord.charAt(i);
            feedbacks.add(new Feedback(letter, Status.Invalid));
        }
        return feedbacks;
    }

    public static List<Feedback> calculateFeedback(String guessedWord, String toGuessWord) {
        StringBuilder stringBuilderGuessedWord = new StringBuilder(guessedWord);
        StringBuilder stringBuilderToGuessWord = new StringBuilder(toGuessWord);
        int[] positions = IntStream.generate(() -> 0).limit(toGuessWord.length()).toArray();
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(new Feedback(guessedWord.charAt(0), Status.Correct));

        for (int i = 1; i < toGuessWord.length(); i++) {
            if (guessedWord.charAt(i) == toGuessWord.charAt(i)) {
                stringBuilderGuessedWord.replace(i, i + 1, "_");
                positions[i] = 2;
            }
        }

        for (int i = 1; i < guessedWord.length(); i++) {
            if (stringBuilderToGuessWord.toString().contains(String.valueOf(stringBuilderGuessedWord.charAt(i)))) {
                stringBuilderToGuessWord = new StringBuilder(stringBuilderToGuessWord.toString().replaceFirst(String.valueOf(stringBuilderToGuessWord.toString().charAt(i)), "_"));
                positions[i] = 1;
            }
        }

        for (int i = 1; i < positions.length; i++) {
            Feedback feedback = new Feedback(guessedWord.charAt(i));
            if (positions[i] == 2) {
                feedback.setStatus(Status.Correct);
            } else if (positions[i] == 1) {
                feedback.setStatus(Status.Present);
            } else {
                feedback.setStatus(Status.Absent);
            }
            feedbacks.add(feedback);
        }
        return feedbacks;
    }
}
