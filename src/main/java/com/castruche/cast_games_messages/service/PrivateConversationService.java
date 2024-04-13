package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dao.PrivateConversationRepository;
import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import com.castruche.cast_games_messages.formatter.MessageFormatter;
import com.castruche.cast_games_messages.formatter.PrivateConversationFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PrivateConversationService extends GenericService<PrivateConversation, PrivateConversationDto>{

    private static final Logger logger = LogManager.getLogger(PrivateConversationService.class);

    private PrivateConversationRepository privateConversationRepository;
    private PrivateConversationFormatter privateConversationFormatter;


    public PrivateConversationService(PrivateConversationRepository privateConversationRepository, PrivateConversationFormatter privateConversationFormatter){
        super(privateConversationRepository, privateConversationFormatter);
        this.privateConversationRepository = privateConversationRepository;
        this.privateConversationFormatter = privateConversationFormatter;
    }



}
