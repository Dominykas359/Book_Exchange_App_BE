package book.exchange.app.controller;

import book.exchange.app.dto.historyDTOs.HistoryRequestDTO;
import book.exchange.app.dto.historyDTOs.HistoryResponseDTO;
import book.exchange.app.service.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping
    public ResponseEntity<HistoryResponseDTO> createHistory(
            @Valid @RequestBody HistoryRequestDTO historyRequestDTO
            ){
        return ResponseEntity.ok(historyService.createHistory(historyRequestDTO));
    }

    @GetMapping("/{id}")
    public List<HistoryResponseDTO> getHistoryByUser(@PathVariable("id") UUID id){
        return historyService.findHistoryByUser(id);
    }
}
