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
        List<PublicationInfo> allPublications = loadAllPublications();

        String publicationsText = formatPublications(allPublications);

        return "User prompt:\n" +
                userPrompt +
                "\n\nMatching these items:\n" +
                publicationsText +
                "\nRespond ONLY with a JSON array of matching UUIDs. Format: [\"uuid1\", \"uuid2\"]";
    }

    public List<Notice> getNotices(String gptTextResponse) {
        List<UUID> ids = parseUuidList(gptTextResponse);
        return loadNoticesByIds(ids);
    }

    private List<UUID> parseUuidList(String json) {
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<UUID>>(){});
        } catch (Exception e) {
            return List.of();
        }
    }

    private List<Notice> loadNoticesByIds(List<UUID> ids) {
        List<Notice> notices = new ArrayList<>();
        for (UUID id : ids) {
            noticeRepository.findByPublicationId(id).ifPresent(notices::add);
        }
        return notices;
    }


    private List<PublicationInfo> loadAllPublications() {
        List<PublicationInfo> result = new ArrayList<>();

        bookRepository.getAllBooks()
                .forEach(b -> result.add(new PublicationInfo(b.getId(), b.getTitle())));

        comicRepository.getAllComics()
                .forEach(c -> result.add(new PublicationInfo(c.getId(), c.getTitle())));

        periodicalRepository.getAllPeriodicals()
                .forEach(p -> result.add(new PublicationInfo(p.getId(), p.getTitle())));

        return result;
    }

    private String formatPublications(List<PublicationInfo> items) {
        StringBuilder builder = new StringBuilder();
        for (PublicationInfo item : items) {
            builder.append(item.id()).append(" - ").append(item.title()).append(" - ");
        }
        return builder.toString();
    }

    private record PublicationInfo(UUID id, String title) {}
}
