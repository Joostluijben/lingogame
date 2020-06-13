package joost.luijben.infrastructure.output.player.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
    Optional<PlayerEntity> getByName(String name);
}
