package book.exchange.app.controller;

import book.exchange.app.dto.commentDTOs.CommentRequestDTO;
import book.exchange.app.dto.commentDTOs.CommentResponseDTO;
import book.exchange.app.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody CommentRequestDTO commentRequestDTO
            ){
        return ResponseEntity.ok(commentService.createComment(commentRequestDTO));
    }

    @GetMapping("/{id}")
    public List<CommentResponseDTO> getCommentsForNotice(
            @PathVariable("id") UUID id
    ){
        return commentService.getCommentsForNotice(id);
    }

    @PutMapping("/{id}")
    public CommentResponseDTO updateComment(
            @PathVariable("id") UUID id,
            @RequestBody CommentRequestDTO commentRequestDTO
    ){
        return commentService.updateComment(id, commentRequestDTO);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable("id") UUID id){
        commentService.deleteComment(id);
    }
}
