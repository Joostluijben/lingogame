package joost.luijben.domain;

public interface PlayerRepository {
    Player createPlayer(String name);
    Player getPlayer(String name);
}
