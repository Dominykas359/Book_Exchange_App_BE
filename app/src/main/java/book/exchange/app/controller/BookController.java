package book.exchange.app.controller;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<BookResponseDTO> getAllBooks(){

        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(bookService.findByBookId(id));
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(
            @PathVariable("id") UUID id,
            @Valid @RequestBody BookRequestDTO bookRequestDTO
    ){
        return bookService.updateBook(id, bookRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") UUID id){
        bookService.deleteBook(id);
    }
}
