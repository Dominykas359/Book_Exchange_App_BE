package book.exchange.app.mapper;

import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.model.Comic;

import java.util.UUID;

public class ComicMapper {

    public static ComicResponseDTO toDto(Comic comic){

        return ComicResponseDTO.builder()
                .id(comic.getId())
                .releaseYear(comic.getReleaseYear())
                .title(comic.getTitle())
                .publisher(comic.getPublisher())
                .author(comic.getAuthor())
                .language(comic.getLanguage())
                .status(comic.getStatus())
                .price(comic.getPrice())
                .pageCount(comic.getPageCount())
                .colored(comic.getColored())
                .build();
    }

    public static Comic fromDto(ComicRequestDTO comicRequestDTO){

        return Comic.builder()
                .id(UUID.randomUUID())
                .releaseYear(comicRequestDTO.getReleaseYear())
                .title(comicRequestDTO.getTitle())
                .publisher(comicRequestDTO.getPublisher())
                .author(comicRequestDTO.getAuthor())
                .language(comicRequestDTO.getLanguage())
                .status(comicRequestDTO.getStatus())
                .price(comicRequestDTO.getPrice())
                .pageCount(comicRequestDTO.getPageCount())
                .colored(comicRequestDTO.getColored())
                .build();
    }
}
