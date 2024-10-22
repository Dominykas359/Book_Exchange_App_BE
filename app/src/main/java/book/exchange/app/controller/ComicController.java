package book.exchange.app.controller;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comics")
public class ComicController {

    private final ComicService comicService;

    @PostMapping
    public ResponseEntity<ComicResponseDTO> createComic(
            @RequestBody ComicRequestDTO comicRequestDTO
            ){
        return ResponseEntity.ok(comicService.createComic(comicRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComicResponseDTO> findComicById(@PathVariable("id") UUID id){

        return ResponseEntity.ok(comicService.findComicById(id));
    }

    @PutMapping("/{id}")
    public ComicResponseDTO updateComic(
            @PathVariable("id") UUID id,
            @RequestBody ComicRequestDTO comicRequestDTO
    ){
        return comicService.updateComic(id, comicRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComic(@PathVariable("id") UUID id){
        comicService.deleteComic(id);
    }
}
