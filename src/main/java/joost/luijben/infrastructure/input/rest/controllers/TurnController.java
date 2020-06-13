package joost.luijben.infrastructure.input.rest.controllers;

import joost.luijben.application.TurnProcessor;
import joost.luijben.domain.RoundDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("turns")
public class TurnController {
    private final TurnProcessor turnProcessor;

    public TurnController(TurnProcessor turnProcessor) {
        this.turnProcessor = turnProcessor;
    }

    @PostMapping("{gameId}/{roundId}/makeTurn")
    public ResponseEntity<RoundDto> makeTurn(@RequestBody String wordInput, @PathVariable Integer roundId, @PathVariable Integer gameId) {
        RoundDto roundDto = turnProcessor.makeTurn(gameId, roundId, wordInput);
        return ResponseEntity.ok(roundDto);
    }
}
