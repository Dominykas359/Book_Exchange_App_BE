package book.exchange.app.mapper;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.model.Book;

import java.util.UUID;

public class BookMapper {

    public static BookResponseDTO toDto(Book book){

        return BookResponseDTO.builder()
                .id(book.getId())
                .releaseYear(book.getReleaseYear())
                .title(book.getTitle())
                .publisher(book.getPublisher())
                .author(book.getAuthor())
                .language(book.getLanguage())
                .status(book.getStatus())
                .price(book.getPrice())
                .pageCount(book.getPageCount())
                .cover(book.getCover())
                .translator(book.getTranslator())
                .build();
    }

    public static Book fromDto(BookRequestDTO bookRequestDTO){

        return Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(bookRequestDTO.getReleaseYear())
                .title(bookRequestDTO.getTitle())
                .publisher(bookRequestDTO.getPublisher())
                .author(bookRequestDTO.getAuthor())
                .language(bookRequestDTO.getLanguage())
                .status(bookRequestDTO.getStatus())
                .price(bookRequestDTO.getPrice())
                .pageCount(bookRequestDTO.getPageCount())
                .cover(bookRequestDTO.getCover())
                .translator(bookRequestDTO.getTranslator())
                .build();
    }
}
