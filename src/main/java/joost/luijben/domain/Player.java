package joost.luijben.domain;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    private Set<Game> games;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }

    public Integer calculateHighScore() {
        return games.stream().mapToInt(Game::getScore).max().orElse(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
