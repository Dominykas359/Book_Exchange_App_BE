package book.exchange.app.service;

import book.exchange.app.dto.chatDTOs.ChatRequestDTO;
import book.exchange.app.dto.chatDTOs.ChatResponseDTO;
import book.exchange.app.mapper.ChatMapper;
import book.exchange.app.mapper.MessagesMapper;
import book.exchange.app.model.Chat;
import book.exchange.app.model.Message;
import book.exchange.app.repository.ChatRepository;
import book.exchange.app.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public ChatResponseDTO createChat(ChatRequestDTO chatRequestDTO){

        Chat chat = ChatMapper.fromDto(chatRequestDTO);
        chatRepository.createChat(chat);
        return ChatMapper.toDto(chat);
    }

    public List<ChatResponseDTO> getChatsByUser(UUID id){

        return chatRepository.getChatsByUser(id)
                .stream()
                .map(ChatMapper::toDto)
                .toList();
    }

    public ChatResponseDTO getChatById(UUID id){
        Chat chat = chatRepository.getChatById(id)
                .orElseThrow(() -> new NoSuchElementException("Chat not found"));

        List<Message> messages = messageRepository.getAllMessages(chat.getId());

        messages.sort((message1, message2) -> message1.getTimeSent().compareTo(message2.getTimeSent()));

        chat.setMessages(messages);

        return ChatMapper.toDto(chat);
    }

    public ChatResponseDTO getChatByNoticeId(UUID id){

        return ChatMapper.toDto(chatRepository.getChatByNoticeId(id)
                .orElseThrow(() -> new NoSuchElementException("Chat not found")));
    }
}
