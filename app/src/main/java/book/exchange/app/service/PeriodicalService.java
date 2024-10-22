package book.exchange.app.service;

import book.exchange.app.dto.comicDTOs.ComicRequestDTO;
import book.exchange.app.dto.comicDTOs.ComicResponseDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalRequestDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalResponseDTO;
import book.exchange.app.mapper.ComicMapper;
import book.exchange.app.mapper.PeriodicalMapper;
import book.exchange.app.model.Comic;
import book.exchange.app.model.Periodical;
import book.exchange.app.model.Status;
import book.exchange.app.repository.PeriodicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PeriodicalService {

    private final PeriodicalRepository periodicalRepository;

    @Transactional
    public PeriodicalResponseDTO createPeriodical(PeriodicalRequestDTO periodicalRequestDTO){

        Periodical periodical = PeriodicalMapper.fromDto(periodicalRequestDTO);
        periodicalRepository.createPeriodical(periodical);
        return PeriodicalMapper.toDto(periodical);
    }

    public PeriodicalResponseDTO findPeriodicalById(UUID id){

        return PeriodicalMapper.toDto(periodicalRepository.findPeriodicalById(id)
                .orElseThrow(() -> new NoSuchElementException("Periodical not found")));
    }

    @Transactional
    public PeriodicalResponseDTO updatePeriodical(UUID id, PeriodicalRequestDTO periodicalRequestDTO){

        Periodical periodical = periodicalRepository.findPeriodicalById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        periodical.setReleaseYear(periodicalRequestDTO.getReleaseYear());
        periodical.setTitle(periodicalRequestDTO.getTitle());
        periodical.setPublisher(periodicalRequestDTO.getPublisher());
        periodical.setAuthor(periodicalRequestDTO.getAuthor());
        periodical.setLanguage(periodicalRequestDTO.getLanguage());
        periodical.setStatus(Status.valueOf(periodicalRequestDTO.getStatus()));
        periodical.setPrice(periodicalRequestDTO.getPrice());
        periodical.setNumber(periodicalRequestDTO.getNumber());
        periodicalRepository.updatePeriodical(periodical);

        return PeriodicalMapper.toDto(periodical);
    }

    @Transactional
    public void deletePeriodical(UUID id) {
        periodicalRepository.findPeriodicalById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
        periodicalRepository.deletePeriodical(id);
    }
}
