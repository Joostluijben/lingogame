package joost.luijben.infrastructure.output.game.jpa;

import joost.luijben.infrastructure.output.player.jpa.PlayerEntity;
import joost.luijben.infrastructure.output.round.jpa.RoundEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class GameEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer score;
    @ManyToOne
    private PlayerEntity player;
    @OneToMany(mappedBy = "game")
    private Set<RoundEntity> rounds;

}
