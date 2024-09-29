package book.exchange.app.service;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.mapper.BookMapper;
import book.exchange.app.model.Book;
import book.exchange.app.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
