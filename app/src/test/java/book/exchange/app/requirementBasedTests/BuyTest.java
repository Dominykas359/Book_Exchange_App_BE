package book.exchange.app.requirementBasedTests;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.model.*;
import book.exchange.app.repository.BookRepository;
import book.exchange.app.repository.NoticeRepository;
import book.exchange.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class BuyTest {
    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;
    private User testBuyer;
    private Notice testNotice;
    private Book testBook;
    private History testHistory;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(UUID.randomUUID())
                .email("testuser@example.com")
                .password("$2a$10$9wM/qnxd5jp6S9nIrX8Lduv/nvSxXHbpEPWmnvq2D6pQshcHznq6W")
                .firstName("Test")
                .lastName("User")
                .birthday(LocalDate.now())
                .role(Role.USER)
                .build();
        userRepository.createUser(testUser);

        testBuyer = User.builder()
                .id(UUID.randomUUID())
                .email("testbuyer@gmail.com")
                .password("$2a$10$9wM/qnxd5jp6S9nIrX8Lduv/nvSxXHbpEPWmnvq2D6pQshcHznq6W")
                .firstName("Test")
                .lastName("Buyer")
                .birthday(LocalDate.now())
                .role(Role.USER)
                .build();
        userRepository.createUser(testBuyer);

        testBook = Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(LocalDate.now())
                .title("Test Book")
                .publisher("Test Publisher")
                .author("John Doe")
                .language("English")
                .status(Status.SELLING)
                .price(25.99)
                .pageCount(300)
                .cover("Hardcover")
                .translator("Jane Doe")
                .build();
        bookRepository.createBook(testBook);

        testNotice = Notice.builder()
                .id(UUID.randomUUID())
                .userId(testUser.getId())
                .bookId(testBook.getId())
                .comicId(null)
                .periodicalId(null)
                .timePosted(LocalDate.now())
                .build();
        noticeRepository.createNotice(testNotice);
    }

    @Test
    public void testSuccessfulStatusChange() throws Exception {
        BookRequestDTO updateDTO = BookRequestDTO.builder()
                .title(testBook.getTitle())
                .author(testBook.getAuthor())
                .publisher(testBook.getPublisher())
                .releaseYear(testBook.getReleaseYear())
                .language(testBook.getLanguage())
                .status("SOLD")
                .price(testBook.getPrice())
                .pageCount(testBook.getPageCount())
                .cover(testBook.getCover())
                .translator(testBook.getTranslator())
                .build();

        String jsonRequest = objectMapper.writeValueAsString(updateDTO);

        mockMvc.perform(put("/books/" + testBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SOLD"));
    }
}
