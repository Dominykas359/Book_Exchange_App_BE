package book.exchange.app.mapper;

import book.exchange.app.dto.chatDTOs.ChatRequestDTO;
import book.exchange.app.dto.chatDTOs.ChatResponseDTO;
import book.exchange.app.model.Chat;

import java.util.UUID;

public class ChatMapper {

    public static ChatResponseDTO toDto(Chat chat){

        return ChatResponseDTO.builder()
                .id(chat.getId())
                .userId(chat.getUserId())
                .noticeId(chat.getNoticeId())
                .messages(chat.getMessages() != null ?
                        chat.getMessages().stream().map(MessagesMapper::toDto).toList()
                        : null)
                .build();
    }

    public static Chat fromDto(ChatRequestDTO chatRequestDTO){

        return Chat.builder()
                .id(UUID.randomUUID())
                .userId(chatRequestDTO.getUserId())
                .noticeId(chatRequestDTO.getNoticeId())
                .build();
    }
}
