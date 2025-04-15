package book.exchange.app.service;

import book.exchange.app.model.*;
import book.exchange.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GPTService {

    private final BookRepository bookRepository;
    private final ComicRepository comicRepository;
    private final PeriodicalRepository periodicalRepository;
    private final NoticeRepository noticeRepository;

    public String preparePromptForJsonResponse(String userPrompt) {
        List<Book> books = bookRepository.getAllBooks();
        List<Comic> comics = comicRepository.getAllComics();
        List<Periodical> periodicals = periodicalRepository.getAllPeriodicals();

        StringBuilder builder = new StringBuilder();

        builder.append("User prompt:\n").append(userPrompt).append("\n\n");

        builder.append("Matching these items:\n");

        for (Book book : books) {
            builder.append(book.getId()).append(" - ").append(book.getTitle()).append(" - ");
        }

        for (Comic comic : comics) {
            builder.append(comic.getId()).append(" - ").append(comic.getTitle()).append(" - ");
        }

        for (Periodical periodical : periodicals) {
            builder.append(periodical.getId()).append(" - ").append(periodical.getTitle()).append(" - ");
        }

        builder.append("\nRespond ONLY with a JSON array of matching UUIDs. Format: [\"uuid1\", \"uuid2\"]");

        return builder.toString();
    }

    public List<Notice> getNotices(String gptTextResponse) {
        List<Notice> notices = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<UUID> ids = objectMapper.readValue(gptTextResponse, new TypeReference<List<UUID>>() {});
            for (UUID id : ids) {
                noticeRepository.findByPublicationId(id).ifPresent(notices::add);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notices;
    }
}
