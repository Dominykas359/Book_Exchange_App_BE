package book.exchange.app.mapper;

import book.exchange.app.dto.messageDTOs.MessageRequestDTO;
import book.exchange.app.dto.messageDTOs.MessageResponseDTO;
import book.exchange.app.model.Message;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class MessagesMapper {

    public static MessageResponseDTO toDto(Message message){

        return MessageResponseDTO.builder()
                .id(message.getId())
                .userId(message.getUserId())
                .chatId(message.getChatId())
                .text(message.getText())
                .timeSent(message.getTimeSent())
                .build();
    }

    public static Message fromDto(MessageRequestDTO messageRequestDTO){

        return Message.builder()
                .id(UUID.randomUUID())
                .userId(messageRequestDTO.getUserId())
                .chatId(messageRequestDTO.getChatId())
                .text(messageRequestDTO.getText())
                .timeSent(LocalTime.now())
                .build();
    }
}
