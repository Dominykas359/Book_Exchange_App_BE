package book.exchange.app.service;

import book.exchange.app.dto.noticeDTOs.NoticeRequestDTO;
import book.exchange.app.dto.noticeDTOs.NoticeResponseDTO;
import book.exchange.app.mapper.NoticeMapper;
import book.exchange.app.model.Filters;
import book.exchange.app.model.Notice;
import book.exchange.app.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponseDTO createNotice(NoticeRequestDTO noticeRequestDTO){

        Notice notice = NoticeMapper.fromDto(noticeRequestDTO);
        noticeRepository.createNotice(notice);
        return NoticeMapper.toDto(notice);
    }

    public List<NoticeResponseDTO> getAllNotices(){

        return noticeRepository.getAllNotices()
                .stream()
                .map(NoticeMapper::toDto)
                .toList();
    }

    public List<NoticeResponseDTO> getAllBookNotices(){

        return noticeRepository.getAllBookNotices()
                .stream()
                .map(NoticeMapper::toDto)
                .toList();
    }

    public List<NoticeResponseDTO> getAllComicNotices(){

        return noticeRepository.getAllComicNotices()
                .stream()
                .map(NoticeMapper::toDto)
                .toList();
    }

    public List<NoticeResponseDTO> getAllPeriodicalNotices(){

        return noticeRepository.getAllPeriodicalNotices()
                .stream()
                .map(NoticeMapper::toDto)
                .toList();
    }

    public NoticeResponseDTO findById(UUID id){

        return NoticeMapper.toDto(noticeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Notice not found")));
    }

    @Transactional
    public void deleteNotice(UUID id){
        noticeRepository.deleteNotice(id);
    }
}
