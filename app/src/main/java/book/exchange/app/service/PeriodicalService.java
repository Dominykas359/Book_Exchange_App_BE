package book.exchange.app.service;

import book.exchange.app.dto.periodicalDTOs.PeriodicalRequestDTO;
import book.exchange.app.dto.periodicalDTOs.PeriodicalResponseDTO;
import book.exchange.app.mapper.PeriodicalMapper;
import book.exchange.app.model.Periodical;
import book.exchange.app.repository.PeriodicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
