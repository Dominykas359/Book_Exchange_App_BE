package book.exchange.app.service;

import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.mapper.ComicMapper;
import book.exchange.app.model.Comic;
import book.exchange.app.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
