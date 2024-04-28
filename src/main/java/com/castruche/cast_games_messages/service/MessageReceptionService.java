package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.dto.PlayerLightDto;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.formatter.MessageFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageReceptionService {

    private static final Logger logger = LogManager.getLogger(MessageReceptionService.class);

    private MessageService messageService;
    private PlayerService playerService;
    private GroupConversationService groupConversationService;
    private PrivateConversationService privateConversationService;

    public MessageReceptionService(MessageService messageService,
                                   PlayerService playerService,
                                   GroupConversationService groupConversationService,
                                   PrivateConversationService privateConversationService) {
        this.messageService = messageService;
        this.groupConversationService = groupConversationService;
        this.privateConversationService = privateConversationService;
        this.playerService = playerService;
    }

    @Transactional
    public void receiveMessage(MessageReceptionDto message) {
        if(message.getSender()==null){
            throw new IllegalArgumentException("Message sender id is required");
        }
        if(message.getMembers()==null || message.getMembers().isEmpty()){
            throw new IllegalArgumentException("Message members ids are required");
        }
        if(message.getContent() == null || message.getContent().isEmpty()){
            throw new IllegalArgumentException("Message content is required");
        }
        List<PlayerLightDto> allPlayers = new ArrayList<>();
        allPlayers.addAll(message.getMembers());
        allPlayers.add(message.getSender());
        playerService.createIfPlayersNotExist(allPlayers);
        if (message.getConversationId() != null) {
            //TODO  C EST ICI QU IL FAUT FAIRE LE TRAITEMENT POUR LES MESSAGES RECUS
        }
        else{
            if(message.getMembers()!=null ){
                if(message.getMembers().size() > 1){
                    this.groupConversationService.createConservation(message);
                }
                else{
                    this.privateConversationService.createConservation(message);
                }
            }
        }
    }

}
