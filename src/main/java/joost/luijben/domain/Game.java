package joost.luijben.domain;

import java.util.HashSet;
import java.util.Set;


public class Game {
    private Integer id;
    private Set<Round> rounds;
    private Integer score;

    public Game() {
        rounds = new HashSet<>();
    }

    public void calculateScoreForGame() {
        score = rounds.stream().mapToInt(Round::getScore).sum();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
