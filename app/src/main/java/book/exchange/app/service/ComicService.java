package book.exchange.app.service;

import book.exchange.app.dto.bookDTOs.BookRequestDTO;
import book.exchange.app.dto.bookDTOs.BookResponseDTO;
import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.mapper.BookMapper;
import book.exchange.app.mapper.ComicMapper;
import book.exchange.app.model.Book;
import book.exchange.app.model.Comic;
import book.exchange.app.model.Status;
import book.exchange.app.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComicService {

    private final ComicRepository comicRepository;

    @Transactional
    public ComicResponseDTO createComic(ComicRequestDTO comicRequestDTO){

        Comic comic = ComicMapper.fromDto(comicRequestDTO);
        comicRepository.createComic(comic);
        return ComicMapper.toDto(comic);
    }

    public ComicResponseDTO findComicById(UUID id){

        return ComicMapper.toDto(comicRepository.findComicById(id)
                .orElseThrow(() -> new NoSuchElementException("Comic not found")));
    }

    @Transactional
    public ComicResponseDTO updateComic(UUID id, ComicRequestDTO comicRequestDTO){

        Comic comic = comicRepository.findComicById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        comic.setReleaseYear(comicRequestDTO.getReleaseYear());
        comic.setTitle(comicRequestDTO.getTitle());
        comic.setPublisher(comicRequestDTO.getPublisher());
        comic.setAuthor(comicRequestDTO.getAuthor());
        comic.setLanguage(comicRequestDTO.getLanguage());
        comic.setStatus(Status.valueOf(comicRequestDTO.getStatus()));
        comic.setPrice(comicRequestDTO.getPrice());
        comic.setPageCount(comicRequestDTO.getPageCount());
        comic.setColored(comicRequestDTO.getColored());
        comicRepository.updateComic(comic);

        return ComicMapper.toDto(comic);
    }

    @Transactional
    public void deleteComic(UUID id) {
        comicRepository.findComicById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        comicRepository.deleteComic(id);
    }
}
