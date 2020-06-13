package joost.luijben.infrastructure.output.player.jpa;

import joost.luijben.domain.Player;
import joost.luijben.domain.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PlayerRepositoryJpaAdapter implements PlayerRepository {
    private final joost.luijben.infrastructure.output.player.jpa.PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerRepositoryJpaAdapter(joost.luijben.infrastructure.output.player.jpa.PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Player createPlayer(String name) {
        Player player = new Player(name);
        PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
        playerRepository.save(playerEntity);
        return player;
    }

    @Override
    public Player getPlayer(String name) {
        Optional<PlayerEntity> playerEntity = playerRepository.getByName(name);
        return modelMapper.map(playerEntity.orElseThrow(EntityNotFoundException::new), Player.class);
    }
}
