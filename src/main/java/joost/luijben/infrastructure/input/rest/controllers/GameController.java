package joost.luijben.infrastructure.input.rest.controllers;


import joost.luijben.application.GameProcessor;
import joost.luijben.domain.Game;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {
    private final GameProcessor gameProcessor;

    public GameController(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
    }

    @PostMapping("{playerName}/startGame")
    public Game startGame(@PathVariable String playerName) {
        return gameProcessor.createGame(playerName);
    }

    @PostMapping("{playerName}/{gameId}/stopGame")
    public Game stopGame(@PathVariable String playerName, @PathVariable Integer gameId) {
        return gameProcessor.stopGame(playerName, gameId);
    }
}
