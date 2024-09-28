package book.exchange.app.mapper;

import book.exchange.app.dto.historyDTOs.HistoryRequestDTO;
import book.exchange.app.dto.historyDTOs.HistoryResponseDTO;
import book.exchange.app.model.History;

import java.util.UUID;

public class HistoryMapper {

    public static HistoryResponseDTO toDto(History history){

        return HistoryResponseDTO.builder()
                .id(history.getId())
                .userId(history.getUserId())
                .noticeId(history.getNoticeId())
                .build();
    }

    public static History fromDto(HistoryRequestDTO historyRequestDTO){

        return History.builder()
                .id(UUID.randomUUID())
                .userId(historyRequestDTO.getUserId())
                .noticeId(historyRequestDTO.getNoticeId())
                .build();
    }
}
