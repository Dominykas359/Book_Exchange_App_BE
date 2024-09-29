package book.exchange.app.controller;

import book.exchange.app.dto.noticeDTOs.NoticeRequestDTO;
import book.exchange.app.dto.noticeDTOs.NoticeResponseDTO;
import book.exchange.app.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<NoticeResponseDTO> createNotice(
            @RequestBody NoticeRequestDTO noticeRequestDTO
    ){
        return ResponseEntity.ok(noticeService.createNotice(noticeRequestDTO));
    }

    @GetMapping
    public List<NoticeResponseDTO> getAllNotices(){
        return noticeService.getAllNotices();
    }

    @GetMapping("/all-books")
    public List<NoticeResponseDTO> getAllBookNotices(){
        return noticeService.getAllBookNotices();
    }

    @GetMapping("/all-comics")
    public List<NoticeResponseDTO> getAllComicNotices(){
        return noticeService.getAllComicNotices();
    }

    @GetMapping("/all-periodicals")
    public List<NoticeResponseDTO> getAllPeriodicalNotices(){
        return noticeService.getAllPeriodicalNotices();
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable("id") UUID id){
        noticeService.deleteNotice(id);
    }
}
