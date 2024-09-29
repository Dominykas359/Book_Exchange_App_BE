package book.exchange.app.controller;

import book.exchange.app.dto.periodicalDTOs.PeriodicalRequestDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalResponseDTO;
import book.exchange.app.service.PeriodicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/periodicals")
public class PeriodicalController {

    private final PeriodicalService periodicalService;

    @PostMapping
    public ResponseEntity<PeriodicalResponseDTO> createPeriodical(
            @RequestBody PeriodicalRequestDTO periodicalRequestDTO
            ){
        return ResponseEntity.ok(periodicalService.createPeriodical(periodicalRequestDTO));
    }
}
