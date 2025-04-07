package book.exchange.app.requirementBasedTests;


import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.model.*;
import book.exchange.app.repository.BookRepository;
import book.exchange.app.repository.NoticeRepository;
import book.exchange.app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
//import org.hamcrest.Matchers.not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;


//import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class FilterTest {


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
    private Book testBookR;
    private Book testBookR1;
    private Book testBookS;
    private Book testBookS1;
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

        testBookR = Book.builder()
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
        bookRepository.createBook(testBookR);

        testBookR1 = Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(LocalDate.now())
                .title("Test Book")
                .publisher("Test Publisher")
                .author("John Doe")
                .language("English")
                .status(Status.RENTED)
                .price(25.99)
                .pageCount(300)
                .cover("Hardcover")
                .translator("Jane Doe")
                .build();
        bookRepository.createBook(testBookR1);

        testBookS = Book.builder()
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
        bookRepository.createBook(testBookS);

        testBookS1 = Book.builder()
                .id(UUID.randomUUID())
                .releaseYear(LocalDate.now())
                .title("Test Book")
                .publisher("Test Publisher")
                .author("John Doe")
                .language("English")
                .status(Status.SOLD)
                .price(25.99)
                .pageCount(300)
                .cover("Hardcover")
                .translator("Jane Doe")
                .build();
        bookRepository.createBook(testBookS1);
    }
    @Test
    public void testSuccessfulFilterRENTING() throws Exception{

        String jsonRequest = objectMapper.writeValueAsString(testBookR);

        mockMvc.perform(get("/books/" + testBookR.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("RENTING"));
    }
    @Test
    public void testUnsuccessfulFilterRENTING() throws Exception{

        String jsonRequest = objectMapper.writeValueAsString(testBookR1);

        mockMvc.perform(get("/books/" + testBookR1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(not("RENTING")));

    }
    @Test
    public void testSuccessfulFilterSELLING() throws Exception{
        String jsonRequest = objectMapper.writeValueAsString(testBookS);

        mockMvc.perform(get("/books/" + testBookS.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("SELLING"));
    }
    @Test
    public void testUnsuccessfulFilterSELLING() throws Exception{

        String jsonRequest = objectMapper.writeValueAsString(testBookS1);

        mockMvc.perform(get("/books/" + testBookS1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(not("SELLING")));

    }
}
