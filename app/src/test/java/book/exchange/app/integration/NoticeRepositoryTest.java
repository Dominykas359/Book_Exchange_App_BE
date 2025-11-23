package book.exchange.app.integration;

import book.exchange.app.model.Book;
import book.exchange.app.model.Notice;
import book.exchange.app.model.Status;
import book.exchange.app.model.User;
import book.exchange.app.repository.BookRepository;
import book.exchange.app.repository.NoticeRepository;
import book.exchange.app.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static book.exchange.app.model.Role.USER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class NoticeRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    private User testUser;
    private Notice notice;

    @BeforeEach
    void setUp() {
        testUser = new User(UUID.randomUUID(), "testuser@example.com", "$2a$10$9wM/qnxd5jp6S9nIrX8Lduv/nvSxXHbpEPWmnvq2D6pQshcHznq6W",
                "Test", "User", LocalDate.of(1990, 1, 1), USER);

        userRepository.createUser(testUser);

        Book book = Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(LocalDate.now())
                .title("Test Book")
                .publisher("Test Publisher")
                .author("John Doe")
                .language("English")
                .status(Status.RENTING)
                .price(25.99)
                .pageCount(300)
                .cover("Hardcover")
                .translator("Jane Doe")
                .build();

        bookRepository.createBook(book);

        notice = Notice.builder()
                .id(UUID.randomUUID())
                .userId(testUser.getId())
                .bookId(book.getId())
                .comicId(null)
                .periodicalId(null)
                .timePosted(LocalDate.now())
                .build();
        noticeRepository.createNotice(notice);
    }

    @Test
    void testCreateNotice() {
        Book newBook = Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(LocalDate.now())
                .title("Test Book")
                .publisher("Test Publisher")
                .author("John Doe")
                .language("English")
                .status(Status.RENTING)
                .price(25.99)
                .pageCount(300)
                .cover("Hardcover")
                .translator("Jane Doe")
                .build();

        bookRepository.createBook(newBook);

        Notice newNotice = Notice.builder()
                .id(UUID.randomUUID())
                .userId(testUser.getId())
                .bookId(newBook.getId())
                .comicId(null)
                .periodicalId(null)
                .timePosted(LocalDate.now())
                .build();

        noticeRepository.createNotice(newNotice);

        Optional<Notice> createdNotice = noticeRepository.findById(newNotice.getId());
        assertTrue(createdNotice.isPresent(), "The notice should be created and present in the database");
    }

    @Test
    public void testFindById() {
        Optional<Notice> foundNotice = noticeRepository.findById(notice.getId());

        assertTrue(foundNotice.isPresent(), "A Notice should be found");
        assertEquals(notice.getId(), foundNotice.get().getId(), "ID should match");
    }

    @Test
    public void testFindByPublicationId() {
        Optional<Notice> foundNotice = noticeRepository.findByPublicationId(notice.getBookId());

        assertTrue(foundNotice.isPresent(), "A notice should be found by bookId");
        assertEquals(notice.getBookId(), foundNotice.get().getBookId(), "Publication ID should match");
    }

    @Test
    public void testGetAllNotices() {
        List<Notice> allNotices = noticeRepository.getAllNotices();

        assertFalse(allNotices.isEmpty(), "Should not be empty");
        assertTrue(allNotices.stream().anyMatch(n -> n.getId().equals(notice.getId())), "The list should contain our Notice");
    }

    @Test
    public void testDeleteNotice() {
        noticeRepository.deleteNotice(notice.getId());

        Optional<Notice> deletedNotice = noticeRepository.findById(notice.getId());

        assertFalse(deletedNotice.isPresent(), "The notice should have been deleted");
    }
}
