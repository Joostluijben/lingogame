package joost.luijben.infrastructure.output.game.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Integer> {
}
