package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.formatter.MessageFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService extends GenericService<Message, MessageDto>{

    private static final Logger logger = LogManager.getLogger(MessageService.class);

    private MessageRepository messageRepository;
    private MessageFormatter messageFormatter;

    private PlayerService playerService;


    public MessageService(MessageRepository messageRepository, MessageFormatter messageFormatter,
                          PlayerService playerService) {
        super(messageRepository, messageFormatter);
        this.messageRepository = messageRepository;
        this.messageFormatter = messageFormatter;
        this.playerService = playerService;
    }


    @Transactional
    public MessageDto receiveAndCreate(MessageReceptionDto receptionDto) {
        if(null==receptionDto.getSender()){
            throw new IllegalArgumentException("Player id is required");
        }
        MessageDto dto = new MessageDto();
        if(null!=receptionDto.getConversationId()){
            dto.setConversationId(receptionDto.getConversationId());
        }
        dto.setContent(receptionDto.getContent());
        Player player = playerService.selectBySourcePlayerId(receptionDto.getSender().getId());
        return createFromConversation(dto, player);
    }

    @Transactional
    public MessageDto createFromConversation(MessageDto dto, Player player) {
        if(null==player){
            throw new IllegalArgumentException("Player id is required");
        }
        Message message = this.messageFormatter.dtoToEntity(dto);
        message.setAuthor(player);
        messageRepository.save(message);
        return this.messageFormatter.entityToDto(message);
    }

}
