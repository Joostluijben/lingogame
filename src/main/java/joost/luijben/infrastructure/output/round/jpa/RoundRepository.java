package joost.luijben.infrastructure.output.round.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<RoundEntity, Integer> {
}
