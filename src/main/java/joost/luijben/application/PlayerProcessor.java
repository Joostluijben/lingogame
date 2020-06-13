package joost.luijben.application;

import joost.luijben.domain.Player;
import joost.luijben.domain.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerProcessor {
    private final PlayerRepository playerRepository;

    public PlayerProcessor(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public Player createPlayer(String name) {
        return playerRepository.createPlayer(name);
    }

    public Player getPlayer(String name) {
        return playerRepository.getPlayer(name);
    }
}
