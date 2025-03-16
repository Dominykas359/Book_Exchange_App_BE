package book.exchange.app.service;

import book.exchange.app.model.Publication;
import book.exchange.app.model.Notice;
import book.exchange.app.repository.GPTRepository;
import book.exchange.app.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class GPTService {

    @Autowired
    private GPTRepository gptRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> getNoticesFromGptResponse(String gptResponse) {
        Pattern pattern = Pattern.compile("\\d+\\. \"(.*?)\" by (.*?)");
        Matcher matcher = pattern.matcher(gptResponse);

        List<String[]> titlesAndAuthors = new ArrayList<>();

        while (matcher.find()) {
            String title = matcher.group(1);
            String author = matcher.group(2);
            titlesAndAuthors.add(new String[]{title, author});
        }

        List<UUID> publicationIds = gptRepository.findPublicationIdsByTitleAndAuthor(titlesAndAuthors);

        return publicationIds.stream()
                .map(publicationId -> noticeRepository.findByPublicationId(publicationId))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
