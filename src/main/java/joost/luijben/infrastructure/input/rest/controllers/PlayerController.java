package joost.luijben.infrastructure.input.rest.controllers;

import joost.luijben.application.PlayerProcessor;
import joost.luijben.domain.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("players")
public class PlayerController {
    private final PlayerProcessor playerProcessor;

    public PlayerController(PlayerProcessor playerProcessor) {
        this.playerProcessor = playerProcessor;
    }

    @GetMapping("{name}/highscore")
    public Integer getHighScoreByPlayer(@PathVariable String name) {
        return playerProcessor.getPlayer(name).calculateHighScore();
    }

    @GetMapping("{name}")
    public ResponseEntity<Player> getPlayer(@PathVariable String name) {
        return ResponseEntity.ok(playerProcessor.getPlayer(name));
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody String name) {
        return ResponseEntity.ok(playerProcessor.createPlayer(name));
    }
}
