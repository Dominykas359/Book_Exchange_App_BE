package book.exchange.app.service;

import book.exchange.app.dto.chatDTOs.ChatRequestDTO;
import book.exchange.app.dto.chatDTOs.ChatResponseDTO;
import book.exchange.app.mapper.ChatMapper;
import book.exchange.app.model.Chat;
import book.exchange.app.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

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
}
