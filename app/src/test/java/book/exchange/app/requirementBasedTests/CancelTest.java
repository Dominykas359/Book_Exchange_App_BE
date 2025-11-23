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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class CancelTest {

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
    private Book rentedBook;
    private Notice testNotice;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(UUID.randomUUID())
                .email("canceluser@example.com")
                .password("password")
                .firstName("Cancel")
                .lastName("User")
                .birthday(LocalDate.now())
                .role(Role.USER)
                .build();
        userRepository.createUser(testUser);

        rentedBook = Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(LocalDate.now())
                .title("Rented Book")
                .publisher("Test Publisher")
                .author("Author")
                .language("English")
                .status(Status.RENTED)
                .price(10.0)
                .pageCount(150)
                .cover("Paperback")
                .translator("Translator")
                .build();
        bookRepository.createBook(rentedBook);

        testNotice = Notice.builder()
                .id(UUID.randomUUID())
                .userId(testUser.getId())
                .bookId(rentedBook.getId())
                .timePosted(LocalDate.now())
                .build();
        noticeRepository.createNotice(testNotice);
    }

    @Test
    public void testStatusChangeFromRENTEDtoRENTING() throws Exception {
        BookRequestDTO updateDTO = BookRequestDTO.builder()
                .title(rentedBook.getTitle())
                .author(rentedBook.getAuthor())
                .publisher(rentedBook.getPublisher())
                .releaseYear(rentedBook.getReleaseYear())
                .language(rentedBook.getLanguage())
                .status("RENTING")
                .price(rentedBook.getPrice())
                .pageCount(rentedBook.getPageCount())
                .cover(rentedBook.getCover())
                .translator(rentedBook.getTranslator())
                .build();

        String jsonRequest = objectMapper.writeValueAsString(updateDTO);

        mockMvc.perform(put("/books/" + rentedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RENTING"));
    }

    @Test
    public void testStatusChangeSpeedUnder1Second() throws Exception {
        BookRequestDTO updateDTO = BookRequestDTO.builder()
                .title(rentedBook.getTitle())
                .author(rentedBook.getAuthor())
                .publisher(rentedBook.getPublisher())
                .releaseYear(rentedBook.getReleaseYear())
                .language(rentedBook.getLanguage())
                .status("RENTING")
                .price(rentedBook.getPrice())
                .pageCount(rentedBook.getPageCount())
                .cover(rentedBook.getCover())
                .translator(rentedBook.getTranslator())
                .build();

        String jsonRequest = objectMapper.writeValueAsString(updateDTO);

        long startTime = System.nanoTime();

        mockMvc.perform(put("/books/" + rentedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());

        long endTime = System.nanoTime();
        long durationInMillis = (endTime - startTime) / 1_000_000;

        assertTrue(durationInMillis < 1000, "Cancel rent took too long: " + durationInMillis + " ms");
    }

    @Test
    public void testRentBookAfterCancel() throws Exception {
        // First cancel rent
        BookRequestDTO cancelRentDTO = BookRequestDTO.builder()
                .title(rentedBook.getTitle())
                .author(rentedBook.getAuthor())
                .publisher(rentedBook.getPublisher())
                .releaseYear(rentedBook.getReleaseYear())
                .language(rentedBook.getLanguage())
                .status("RENTING")
                .price(rentedBook.getPrice())
                .pageCount(rentedBook.getPageCount())
                .cover(rentedBook.getCover())
                .translator(rentedBook.getTranslator())
                .build();

        String jsonCancel = objectMapper.writeValueAsString(cancelRentDTO);

        mockMvc.perform(put("/books/" + rentedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCancel))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RENTING"));

        // Then rent again
        BookRequestDTO rentAgainDTO = BookRequestDTO.builder()
                .title(rentedBook.getTitle())
                .author(rentedBook.getAuthor())
                .publisher(rentedBook.getPublisher())
                .releaseYear(rentedBook.getReleaseYear())
                .language(rentedBook.getLanguage())
                .status("RENTED")
                .price(rentedBook.getPrice())
                .pageCount(rentedBook.getPageCount())
                .cover(rentedBook.getCover())
                .translator(rentedBook.getTranslator())
                .build();

        String jsonRentAgain = objectMapper.writeValueAsString(rentAgainDTO);

        mockMvc.perform(put("/books/" + rentedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRentAgain))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RENTED"));
    }
}
