package joost.luijben.infrastructure.output.player.jpa;

import joost.luijben.infrastructure.output.game.jpa.GameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class PlayerEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "player")
    private Set<GameEntity> games;

    public PlayerEntity() {
    }
}
