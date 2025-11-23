package book.exchange.app.unit;

import book.exchange.app.model.Book;
import book.exchange.app.model.Comic;
import book.exchange.app.model.Notice;
import book.exchange.app.model.Periodical;
import book.exchange.app.repository.BookRepository;
import book.exchange.app.repository.ComicRepository;
import book.exchange.app.repository.NoticeRepository;
import book.exchange.app.repository.PeriodicalRepository;
import book.exchange.app.service.GPTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GPTServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ComicRepository comicRepository;

    @Mock
    private PeriodicalRepository periodicalRepository;

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private GPTService gptService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // -------------------------------------------------------------------------
    // 1. Tests for preparePromptForJsonResponse()
    // -------------------------------------------------------------------------
    @Test
    void testPreparePromptForJsonResponse() {
        // Arrange repository data
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle("BookTitle");

        Comic comic = new Comic();
        comic.setId(UUID.randomUUID());
        comic.setTitle("ComicTitle");

        Periodical periodical = new Periodical();
        periodical.setId(UUID.randomUUID());
        periodical.setTitle("PeriodicalTitle");

        when(bookRepository.getAllBooks()).thenReturn(List.of(book));
        when(comicRepository.getAllComics()).thenReturn(List.of(comic));
        when(periodicalRepository.getAllPeriodicals()).thenReturn(List.of(periodical));

        // Act
        String result = gptService.preparePromptForJsonResponse("Find items");

        // Assert
        assertTrue(result.contains("User prompt:"));
        assertTrue(result.contains("Find items"));
        assertTrue(result.contains(book.getId().toString()));
        assertTrue(result.contains(comic.getId().toString()));
        assertTrue(result.contains(periodical.getId().toString()));
        assertTrue(result.contains("Respond ONLY with a JSON array"));
    }

    // -------------------------------------------------------------------------
    // 2. Tests for getNotices() → Valid JSON, notices found
    // -------------------------------------------------------------------------
    @Test
    void testGetNoticesValidJson() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Notice notice1 = new Notice();
        Notice notice2 = new Notice();

        when(noticeRepository.findByPublicationId(id1)).thenReturn(Optional.of(notice1));
        when(noticeRepository.findByPublicationId(id2)).thenReturn(Optional.of(notice2));

        String jsonResponse = new ObjectMapper().writeValueAsString(List.of(id1, id2));

        // Act
        List<Notice> notices = gptService.getNotices(jsonResponse);

        // Assert
        assertEquals(2, notices.size());
        assertTrue(notices.contains(notice1));
        assertTrue(notices.contains(notice2));
    }

    // -------------------------------------------------------------------------
    // 3. Valid JSON, some notices not found
    // -------------------------------------------------------------------------
    @Test
    void testGetNoticesSomeMissing() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Notice notice = new Notice();

        when(noticeRepository.findByPublicationId(id1)).thenReturn(Optional.of(notice));
        when(noticeRepository.findByPublicationId(id2)).thenReturn(Optional.empty());

        String jsonResponse = new ObjectMapper().writeValueAsString(List.of(id1, id2));

        List<Notice> notices = gptService.getNotices(jsonResponse);

        assertEquals(1, notices.size());
        assertTrue(notices.contains(notice));
    }

    // -------------------------------------------------------------------------
    // 4. Invalid JSON → catch block executed
    // -------------------------------------------------------------------------
    @Test
    void testGetNoticesInvalidJson() {
        String invalidJson = "this_is_not_json";

        List<Notice> notices = gptService.getNotices(invalidJson);

        assertTrue(notices.isEmpty()); // Empty list expected
    }

    // -------------------------------------------------------------------------
    // 5. Empty JSON list
    // -------------------------------------------------------------------------
    @Test
    void testGetNoticesEmptyList() throws Exception {
        String jsonResponse = "[]";

        List<Notice> notices = gptService.getNotices(jsonResponse);

        assertTrue(notices.isEmpty());
        verifyNoInteractions(noticeRepository);
    }
}
