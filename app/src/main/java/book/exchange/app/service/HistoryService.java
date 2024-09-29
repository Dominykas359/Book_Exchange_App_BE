package book.exchange.app.service;

import book.exchange.app.dto.historyDTOs.HistoryRequestDTO;
import book.exchange.app.dto.historyDTOs.HistoryResponseDTO;
import book.exchange.app.mapper.HistoryMapper;
import book.exchange.app.model.History;
import book.exchange.app.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Transactional
    public HistoryResponseDTO createHistory(HistoryRequestDTO historyRequestDTO){

        History history = HistoryMapper.fromDto(historyRequestDTO);
        historyRepository.createHistory(history);
        return HistoryMapper.toDto(history);
    }

    public List<HistoryResponseDTO> findHistoryByUser(UUID id){

        return historyRepository.findByUser(id)
                .stream()
                .map(HistoryMapper::toDto)
                .toList();
    }

}
