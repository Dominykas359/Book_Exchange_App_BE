package book.exchange.app.controller;

import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalRequestDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalResponseDTO;
import book.exchange.app.service.PeriodicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<PeriodicalResponseDTO> findPeriodicalById(@PathVariable("id") UUID id){

        return ResponseEntity.ok(periodicalService.findPeriodicalById(id));
    }

    @PutMapping("/{id}")
    public PeriodicalResponseDTO updatePeriodical(
            @PathVariable("id") UUID id,
            @RequestBody PeriodicalRequestDTO periodicalRequestDTO
    ){
        return periodicalService.updatePeriodical(id, periodicalRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePeriodical(@PathVariable("id") UUID id){
        periodicalService.deletePeriodical(id);
    }
}
