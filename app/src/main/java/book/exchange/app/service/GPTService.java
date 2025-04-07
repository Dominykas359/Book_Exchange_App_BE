package book.exchange.app.service;

import book.exchange.app.model.*;
import book.exchange.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
@RequiredArgsConstructor
public class GPTService {

    private final BookRepository bookRepository;
    private final ComicRepository comicRepository;
    private final PeriodicalRepository periodicalRepository;
    private final NoticeRepository noticeRepository;

    public List<Notice> getNoticesFromGptResponse(String gptResponse) {
        Pattern pattern = Pattern.compile("\"(.*?)\" by");
        Matcher matcher = pattern.matcher(gptResponse);

        List<String> bookTitles = new ArrayList<>();

        while (matcher.find()) {
            bookTitles.add(matcher.group(1));
        }

        List<Book> books = new ArrayList<>();
        List<Comic> comics = new ArrayList<>();
        List<Periodical> periodicals = new ArrayList<>();

        for (String title : bookTitles) {
            books.addAll(bookRepository.getBooksByTitle(title));
            comics.addAll(comicRepository.getComicsByTitle(title));
            periodicals.addAll(periodicalRepository.getPeriodicalsByTitle(title));
        }

        List<Notice> notices = new ArrayList<>();

        for (Book book : books) {
            noticeRepository.findByPublicationId(book.getId()).ifPresent(notices::add);
        }
        for (Comic comic : comics) {
            noticeRepository.findByPublicationId(comic.getId()).ifPresent(notices::add);
        }
        for (Periodical periodical : periodicals) {
            noticeRepository.findByPublicationId(periodical.getId()).ifPresent(notices::add);
        }

        return notices;
    }

}
