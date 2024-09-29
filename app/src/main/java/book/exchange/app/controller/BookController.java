package book.exchange.app.controller;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(
            @RequestBody BookRequestDTO bookRequestDTO
            ){
        return ResponseEntity.ok(bookService.createBook(bookRequestDTO));
    }
}
