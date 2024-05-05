package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dto.*;
import com.castruche.cast_games_messages.entity.*;
import com.castruche.cast_games_messages.enums.ConversationType;
import com.castruche.cast_games_messages.formatter.ConversationFormatter;
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

    private ConversationService conversationService;

    private ConversationFormatter conversationFormatter;
    private GroupConversationService groupConversationService;
    private PrivateConversationService privateConversationService;

    private SseService sseService;

    public MessageReceptionService(MessageService messageService,
                                   PlayerService playerService,
                                   GroupConversationService groupConversationService,
                                   PrivateConversationService privateConversationService,
                                   ConversationFormatter conversationFormatter,
                                   SseService sseService,
                                   ConversationService conversationService) {
        this.messageService = messageService;
        this.groupConversationService = groupConversationService;
        this.privateConversationService = privateConversationService;
        this.playerService = playerService;
        this.conversationService = conversationService;
        this.sseService = sseService;
        this.conversationFormatter = conversationFormatter;
    }

    @Transactional
    public void receiveMessage(MessageReceptionDto message) {
        boolean isNewConversation = message.getConversationId() == null;
        if (message.getSender() == null) {
            throw new IllegalArgumentException("Message sender id is required");
        }
        if (isNewConversation && (message.getMembers() == null || message.getMembers().isEmpty())) {
            throw new IllegalArgumentException("Message members ids are required");
        }
        if (message.getContent() == null || message.getContent().isEmpty()) {
            throw new IllegalArgumentException("Message content is required");
        }
        //TOUT D ABORD ON ENREGISTRE LES JOUEURS SI ILS N EXISTENT PAS
        updateConversationMembers(message);

        //SI CONVERSATION EXISTE DEJA ON AJOUTE LE MESSAGE

        Conversation updatedConversation = null;
        if (!isNewConversation) {
            messageService.receiveAndCreate(message);
            updatedConversation = conversationService.selectById(message.getConversationId());
        }
        //SINON ON CREE LA CONVERSATION
        else {
            if (message.getMembers() != null) {
                if (message.getMembers().size() > 1) {
                    updatedConversation = this.groupConversationService.createConservation(message);
                } else {
                    updatedConversation = this.privateConversationService.createConservation(message);
                }
            }
        }
        updateConversationForMembers(updatedConversation);
    }

    private void updateConversationMembers(MessageReceptionDto message){
        List<PlayerLightDto> allPlayers = new ArrayList<>();
        if (null != message.getMembers()) {
            allPlayers.addAll(message.getMembers());
        }
        allPlayers.add(message.getSender());
        playerService.createIfPlayersNotExist(allPlayers);
    }

    private void updateConversationForMembers(Conversation updatedConversation) {
        if (updatedConversation == null) {
            return;
        }
        ConversationDto updatedConversationDto = conversationFormatter.entityToDto(updatedConversation);
        List<Player> members = new ArrayList<>();
        if (updatedConversationDto.getType().equals(ConversationType.GROUP_CONVERSATION)) {
            members.addAll(((GroupConversation) updatedConversation).getPlayers());
        } else if (updatedConversationDto.getType().equals(ConversationType.PRIVATE_CONVERSATION)) {
            PrivateConversationDto privateConversationDto = (PrivateConversationDto) updatedConversationDto;
            updatedConversationDto.setName(privateConversationService.getConversationName(privateConversationDto, privateConversationDto.getPlayer1().getId()));
            members.add(((PrivateConversation) updatedConversation).getPlayer1());
            members.add(((PrivateConversation) updatedConversation).getPlayer2());
        }
        for (Player player : members) {
            sseService.updateConversationForPlayer(player.getSourcePlayerId(), updatedConversationDto);
        }
    }

}
