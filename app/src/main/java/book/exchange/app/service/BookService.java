package book.exchange.app.service;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.mapper.BookMapper;
import book.exchange.app.model.Book;
import book.exchange.app.model.Status;
import book.exchange.app.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO){

        Book book = BookMapper.fromDto(bookRequestDTO);
        bookRepository.createBook(book);
        return BookMapper.toDto(book);
    }

    public BookResponseDTO findByBookId(UUID id){

        return BookMapper.toDto(bookRepository.findByBookId(id)
                .orElseThrow(() -> new NoSuchElementException("Book now found")));
    }

    @Transactional
    public BookResponseDTO updateBook(UUID id, BookRequestDTO bookRequestDTO){

        Book book = bookRepository.findByBookId(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        book.setReleaseYear(bookRequestDTO.getReleaseYear());
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublisher(bookRequestDTO.getPublisher());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setLanguage(bookRequestDTO.getLanguage());
        book.setStatus(Status.valueOf(bookRequestDTO.getStatus()));
        book.setPrice(bookRequestDTO.getPrice());
        book.setPageCount(bookRequestDTO.getPageCount());
        book.setCover(bookRequestDTO.getCover());
        book.setTranslator(bookRequestDTO.getTranslator());
        bookRepository.updateBook(book);

        return BookMapper.toDto(book);
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.findByBookId(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        bookRepository.deleteBook(id);
    }
}
