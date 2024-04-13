package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.formatter.MessageFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MessageReceptionService {

    private static final Logger logger = LogManager.getLogger(MessageReceptionService.class);

    public void receiveMessage(MessageDto message) {
        logger.info("Message received: " + message);
    }


}