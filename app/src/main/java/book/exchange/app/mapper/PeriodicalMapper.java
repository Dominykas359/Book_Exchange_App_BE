package book.exchange.app.mapper;

import book.exchange.app.dto.periodicalDTOs.PeriodicalRequestDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalResponseDTO;
import book.exchange.app.model.Periodical;
import book.exchange.app.model.Status;

import java.util.UUID;

public class PeriodicalMapper {

    public static PeriodicalResponseDTO toDto(Periodical periodical){

        return PeriodicalResponseDTO.builder()
                .id(periodical.getId())
                .releaseYear(periodical.getReleaseYear())
                .title(periodical.getTitle())
                .publisher(periodical.getPublisher())
                .author(periodical.getAuthor())
                .language(periodical.getLanguage())
                .status(periodical.getStatus().name())
                .price(periodical.getPrice())
                .number(periodical.getNumber())
                .build();
    }

    public static Periodical fromDto(PeriodicalRequestDTO periodicalRequestDTO){

        return Periodical.builder()
                .id(UUID.randomUUID())
                .releaseYear(periodicalRequestDTO.getReleaseYear())
                .title(periodicalRequestDTO.getTitle())
                .publisher(periodicalRequestDTO.getPublisher())
                .author(periodicalRequestDTO.getAuthor())
                .language(periodicalRequestDTO.getLanguage())
                .status(Status.valueOf(periodicalRequestDTO.getStatus()))
                .price(periodicalRequestDTO.getPrice())
                .number(periodicalRequestDTO.getNumber())
                .build();
    }
}
