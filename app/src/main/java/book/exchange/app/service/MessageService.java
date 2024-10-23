package book.exchange.app.service;

import book.exchange.app.dto.messageDTOs.MessageRequestDTO;
import book.exchange.app.dto.messageDTOs.MessageResponseDTO;
import book.exchange.app.mapper.MessagesMapper;
import book.exchange.app.model.Message;
import book.exchange.app.repository.MessageRepository;
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
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public MessageResponseDTO createMessage(MessageRequestDTO messageRequestDTO){

        Message message = MessagesMapper.fromDto(messageRequestDTO);
        messageRepository.createMessage(message);
        return MessagesMapper.toDto(message);
    }

    public List<MessageResponseDTO> getAllMessages(UUID id){

        return messageRepository.getAllMessages(id)
                .stream()
                .map(MessagesMapper::toDto)
                .toList();
    }

    @Transactional
    public MessageResponseDTO updateMessage(UUID id, MessageRequestDTO messageRequestDTO){

        Message message = messageRepository.findMessageById(id)
                .orElseThrow(() -> new NoSuchElementException("Message not found"));

        message.setText(messageRequestDTO.getText());
        message.setTimeSent(LocalDateTime.now());
        messageRepository.updateMessage(message);
        return MessagesMapper.toDto(message);
    }

    @Transactional
    public void deleteMessage(UUID id){
        messageRepository.deleteMessage(id);
    }
}
