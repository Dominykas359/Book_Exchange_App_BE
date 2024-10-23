package book.exchange.app.mapper;

import book.exchange.app.dto.commentDTOs.CommentRequestDTO;
import book.exchange.app.dto.commentDTOs.CommentResponseDTO;
import book.exchange.app.model.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class CommentMapper {

    public static CommentResponseDTO toDto(Comment comment){

        return CommentResponseDTO.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .timeSent(comment.getTimeSent())
                .content(comment.getContent())
                .commentId(comment.getCommentId())
                .noticeId(comment.getNoticeId())
                .replies(comment.getReplies() != null ?
                        comment.getReplies().stream().map(CommentMapper::toDto).toList()
                        : null)
                .build();
    }

    public static Comment fromDto(CommentRequestDTO commentRequestDTO){

        return Comment.builder()
                .id(UUID.randomUUID())
                .userId(commentRequestDTO.getUserId())
                .timeSent(LocalDateTime.now())
                .content(commentRequestDTO.getContent())
                .commentId(commentRequestDTO.getCommentId())
                .noticeId(commentRequestDTO.getNoticeId())
                .build();
    }
}
