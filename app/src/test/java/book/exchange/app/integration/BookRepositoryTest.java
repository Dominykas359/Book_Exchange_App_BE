package book.exchange.app.integration;

import book.exchange.app.model.Book;
import book.exchange.app.model.Status;
import book.exchange.app.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional  // Ensures each test runs in isolation
@Rollback       // Ensures database resets after each test
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testCreateBook() {
        // Given - Creating a book object
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setReleaseYear(LocalDate.now());
        book.setTitle("Test Book");
        book.setPublisher("Test Publisher");
        book.setAuthor("John Doe");
        book.setLanguage("English");
        book.setStatus(Status.RENTING);
        book.setPrice(25.99);
        book.setPageCount(300);
        book.setCover("Hardcover");
        book.setTranslator("Jane Doe");

        // When - Insert into database
        bookRepository.createBook(book);

        // Then - Retrieve and verify it exists
        List<Book> books = bookRepository.getAllBooks();
        assertFalse(books.isEmpty());
        assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("Test Book")));
    }
}
