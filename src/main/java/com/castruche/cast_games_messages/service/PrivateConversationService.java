package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.MessageRepository;
import com.castruche.cast_games_messages.dao.PrivateConversationRepository;
import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import com.castruche.cast_games_messages.enums.ConversationType;
import com.castruche.cast_games_messages.formatter.MessageFormatter;
import com.castruche.cast_games_messages.formatter.PrivateConversationFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivateConversationService extends GenericService<PrivateConversation, PrivateConversationDto> implements IConversationService{

    private static final Logger logger = LogManager.getLogger(PrivateConversationService.class);

    private PrivateConversationRepository privateConversationRepository;
    private PrivateConversationFormatter privateConversationFormatter;
    private PlayerService playerService;
    private MessageService messageService;


    public PrivateConversationService(PrivateConversationRepository privateConversationRepository, 
                                      PlayerService playerService,
                                      MessageService messageService,
                                      PrivateConversationFormatter privateConversationFormatter){
        super(privateConversationRepository, privateConversationFormatter);
        this.privateConversationRepository = privateConversationRepository;
        this.privateConversationFormatter = privateConversationFormatter;
        this.playerService = playerService;
        this.messageService = messageService;
    }

    @Override
    public void createConservation(MessageReceptionDto messageReceptionDto) {
        PrivateConversation privateConversation = new PrivateConversation();
        Player player1 = this.playerService.selectBySourcePlayerId(messageReceptionDto.getSender().getId());
        Player player2 = this.playerService.selectBySourcePlayerId(messageReceptionDto.getMembers().get(0).getId());
        privateConversation.setPlayer1(player1);
        privateConversation.setPlayer2(player2);
        privateConversation.setMessages(new ArrayList<>());
        privateConversation = privateConversationRepository.save(privateConversation);
        MessageDto message = new MessageDto();
        message.setConversationType(ConversationType.PRIVATE_CONVERSATION);
        message.setAuthor(player1);
        message.setContent(messageReceptionDto.getContent());
        message.setConversationId(privateConversation.getId());
        message = messageService.create(message);
    }

    public List<PrivateConversationDto> findByPlayerId(Long playerId){
        List<PrivateConversation> entities = privateConversationRepository.findByPlayer1IdOrPlayer2Id(playerId, playerId);
        List<PrivateConversationDto> results = privateConversationFormatter.entityToDto(entities);
        for(PrivateConversationDto dto : results){
            if(null!=dto.getPlayer1() && null!=dto.getPlayer2()){
                if(dto.getPlayer1().getId().equals(playerId)){
                    dto.setName(dto.getPlayer2().getUsername());
                } else {
                    dto.setName(dto.getPlayer1().getUsername());
                }
            }
        }
        return results;
    }



}
