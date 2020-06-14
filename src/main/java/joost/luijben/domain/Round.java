package joost.luijben.domain;

import java.time.Instant;
import java.util.*;


public class Round {
    private final Integer maxTurnAmount = 5;
    private static Integer POINTS_PER_TURN = 10;
    private static Integer TIMER_SECONDS = 10;
    private boolean won;
    private Integer id;
    private List<Turn> turns;
    private String word;
    private Integer wordLength;
    private Integer score = 0;

    public Round() {
    }

    public Round(Set<Word> words, Integer wordLength) {
        won = false;
        turns = new ArrayList<>();
        word = Objects.requireNonNull(words.stream().skip(new Random().nextInt(words.size())).findFirst().orElse(null)).getValue();
        this.wordLength = wordLength;
    }

    public RoundDto makeTurn(String inputWord, Set<Word> words) {
        Turn turn = new Turn();
        if (!won && !(turns.size() >= maxTurnAmount)) {
            if (turns.size() > 0) {
                if (turn.getTime().plusSeconds(TIMER_SECONDS).compareTo(Instant.now()) >= 0) {
                    calculateTurn(inputWord, words, turn);
                } else {
                    turn.setFeedbacks(CheckWordService.renderWordInvalid(inputWord));
                }
            } else {
                calculateTurn(inputWord, words, turn);
            }
        } else {
            for (int i = 0; i < inputWord.length(); i++) {
                turn.getFeedbacks().add(new Feedback(word.charAt(i), Status.Correct));
            }
        }
        turns.add(turn);
        calculateScoreForRound();
        return RoundToRoundDtoMapper.map(this);
    }

    private void calculateTurn(String inputWord, Set<Word> words, Turn turn) {
        if (CheckWordService.isWordValid(inputWord, word, words)) {
            List<Feedback> feedbacks = CheckWordService.calculateFeedback(inputWord, word);
            if (feedbacks.stream().allMatch(feedback -> feedback.getStatus().equals(Status.Correct))) {
                won = true;
            }
            turn.setFeedbacks(feedbacks);
        } else {
            turn.setFeedbacks(CheckWordService.renderWordInvalid(inputWord));
        }
    }

    public void calculateScoreForRound() {
        score = (maxTurnAmount - this.turns.size()) * POINTS_PER_TURN;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public void setWordLength(Integer wordLength) {
        this.wordLength = wordLength;
    }

    public Integer getScore() {
        return score;
    }
}
