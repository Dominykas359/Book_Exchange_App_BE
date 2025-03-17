package book.exchange.app;

import book.exchange.app.controller.BookController;
import book.exchange.app.controller.ComicController;
import book.exchange.app.controller.PeriodicalController;
import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalRequestDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalResponseDTO;
import book.exchange.app.service.BookService;
import book.exchange.app.service.ComicService;
import book.exchange.app.service.PeriodicalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

public class BuyTests {

    @Mock
    private BookService bookService;

    @Mock
    private ComicService comicService;

    @Mock
    private PeriodicalService periodicalService;

    @InjectMocks
    private BookController bookController;

    @InjectMocks
    private ComicController comicController;

    @InjectMocks
    private PeriodicalController periodicalController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookController, comicController, periodicalController)
                .build();
    }

    @Test
    public void testUpdateBook() throws Exception {
        UUID bookId = UUID.randomUUID();

        // Užpildykime visus privalomus laukus
        BookRequestDTO requestDTO = BookRequestDTO.builder()
                .pageCount(100)
                .cover("Cover Image")
                .translator("Translator")
                .build();

        BookResponseDTO responseDTO = BookResponseDTO.builder()
                .id(bookId)
                .pageCount(100)
                .cover("Cover Image")
                .translator("Translator")
                .build();

        when(bookService.updateBook(eq(bookId), any(BookRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pageCount\":100, \"cover\":\"Cover Image\", \"translator\":\"Translator\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId.toString()))
                .andExpect(jsonPath("$.pageCount").value(100))
                .andExpect(jsonPath("$.cover").value("Cover Image"))
                .andExpect(jsonPath("$.translator").value("Translator"));

        verify(bookService, times(1)).updateBook(eq(bookId), any(BookRequestDTO.class));
    }

    @Test
    public void testUpdateComic() throws Exception {
        UUID comicId = UUID.randomUUID();

        ComicRequestDTO requestDTO = ComicRequestDTO.builder()
                .pageCount(50)
                .colored(true)
                .build();

        ComicResponseDTO responseDTO = ComicResponseDTO.builder()
                .id(comicId)
                .pageCount(50)
                .colored(true)
                .build();

        when(comicService.updateComic(eq(comicId), any(ComicRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/comics/{id}", comicId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pageCount\":50, \"colored\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(comicId.toString()))
                .andExpect(jsonPath("$.pageCount").value(50))
                .andExpect(jsonPath("$.colored").value(true));

        verify(comicService, times(1)).updateComic(eq(comicId), any(ComicRequestDTO.class));
    }

    @Test
    public void testUpdatePeriodical() throws Exception {
        UUID periodicalId = UUID.randomUUID();

        PeriodicalRequestDTO requestDTO = PeriodicalRequestDTO.builder()
                .number(10)
                .build();

        PeriodicalResponseDTO responseDTO = PeriodicalResponseDTO.builder()
                .id(periodicalId)
                .number(10)
                .build();

        when(periodicalService.updatePeriodical(eq(periodicalId), any(PeriodicalRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/periodicals/{id}", periodicalId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"number\":10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(periodicalId.toString()))
                .andExpect(jsonPath("$.number").value(10));

        verify(periodicalService, times(1)).updatePeriodical(eq(periodicalId), any(PeriodicalRequestDTO.class));
    }
}
