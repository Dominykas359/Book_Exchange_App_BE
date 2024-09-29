package book.exchange.app.mapper;

import book.exchange.app.dto.noticeDTOs.NoticeRequestDTO;
import book.exchange.app.dto.noticeDTOs.NoticeResponseDTO;
import book.exchange.app.model.Notice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class NoticeMapper {

    public static NoticeResponseDTO toDto(Notice notice){

        return NoticeResponseDTO.builder()
                .id(notice.getId())
                .userId(notice.getUserId())
                .bookId(notice.getBookId())
                .comicId(notice.getComicId())
                .periodicalId(notice.getPeriodicalId())
                .timePosted(notice.getTimePosted())
                .build();
    }

    public static Notice fromDto(NoticeRequestDTO noticeRequestDTO){

        return Notice.builder()
                .id(UUID.randomUUID())
                .userId(noticeRequestDTO.getUserId())
                .bookId(noticeRequestDTO.getBookId())
                .comicId(noticeRequestDTO.getComicId())
                .periodicalId(noticeRequestDTO.getPeriodicalId())
                .timePosted(LocalDate.now())
                .build();
    }
}
