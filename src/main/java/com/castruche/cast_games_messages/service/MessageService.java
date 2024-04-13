package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.entity.Message;
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


    public MessageService(MessageRepository messageRepository, MessageFormatter messageFormatter){
        super(messageRepository, messageFormatter);
        this.messageRepository = messageRepository;
        this.messageFormatter = messageFormatter;
    }

    @Transactional
    public MessageDto send(MessageDto messageDto){
        Message message = messageFormatter.dtoToEntity(messageDto);
        message = messageRepository.save(message);
        return messageFormatter.entityToDto(message);
    }


}
