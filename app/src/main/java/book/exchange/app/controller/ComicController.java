package book.exchange.app.controller;

import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
