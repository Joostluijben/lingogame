package joost.luijben.infrastructure.output.player.jpa;

import joost.luijben.infrastructure.output.game.jpa.GameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class PlayerEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "player")
    private Set<GameEntity> games;

    public PlayerEntity() {
    }
}
