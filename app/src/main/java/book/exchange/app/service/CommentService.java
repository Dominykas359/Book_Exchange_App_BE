package book.exchange.app.service;

import book.exchange.app.dto.commentDTOs.CommentRequestDTO;
import book.exchange.app.dto.commentDTOs.CommentResponseDTO;
import book.exchange.app.mapper.CommentMapper;
import book.exchange.app.model.Comment;
import book.exchange.app.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO){
        Comment comment = CommentMapper.fromDto(commentRequestDTO);
        commentRepository.createComment(comment);
        return CommentMapper.toDto(comment);
    }

    private void populateReplies(Comment comment) {

        List<Comment> replies = commentRepository.getRepliesForComment(comment.getId());
        comment.setReplies(replies);

        for (Comment reply : replies) {
            populateReplies(reply);
        }
    }

    public List<CommentResponseDTO> getCommentsForNotice(UUID noticeId) {

        List<Comment> topLevelComments = commentRepository.getCommentForNotice(noticeId);
        for (Comment comment : topLevelComments) {
            populateReplies(comment);
        }

        return topLevelComments.stream()
                .map(CommentMapper::toDto)
                .toList();
    }

    @Transactional
    public CommentResponseDTO updateComment(UUID id, CommentRequestDTO commentRequestDTO){
        Comment comment = commentRepository.findCommentById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));
        comment.setContent(commentRequestDTO.getContent());
        comment.setTimeSent(LocalDateTime.now());
        commentRepository.updateComment(comment);
        return CommentMapper.toDto(comment);
    }

    @Transactional
    public void deleteComment(UUID id){
        commentRepository.deleteComment(id);
    }
}
