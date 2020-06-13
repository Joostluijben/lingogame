package joost.luijben.infrastructure.output.round.jpa;

import joost.luijben.domain.Turn;
import joost.luijben.infrastructure.output.game.jpa.GameEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
@Getter
@Setter
public class RoundEntity {
    @GeneratedValue
    @Id
    private Integer id;
    @ManyToOne
    private GameEntity game;
    private Time time;
    private String word;
    @Transient
    private Set<Turn> turns;

    public RoundEntity() {
    }

    @Override
    public String toString() {
        return "RoundEntity{" +
                "id=" + id +
                ", game=" + game +
                ", time=" + time;
    }
}
