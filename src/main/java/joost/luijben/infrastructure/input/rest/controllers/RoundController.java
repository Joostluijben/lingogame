package joost.luijben.infrastructure.input.rest.controllers;

import joost.luijben.application.RoundProcessor;
import joost.luijben.domain.RoundDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rounds")
public class RoundController {
    private final RoundProcessor roundController;

    public RoundController(RoundProcessor roundController) {
        this.roundController = roundController;
    }

    @PostMapping("{gameId}/startRound")
    public ResponseEntity<RoundDto> initializeRound(@RequestParam(required = false) Integer wordLength, @PathVariable Integer gameId) {
        RoundDto roundDto = roundController.startRound(gameId, wordLength);
        return ResponseEntity.ok(roundDto);
    }
}
