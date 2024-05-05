package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.GroupConversationRepository;
import com.castruche.cast_games_messages.dto.*;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.GroupConversation;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.enums.ConversationType;
import com.castruche.cast_games_messages.formatter.GroupConversationFormatter;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupConversationService extends GenericService<GroupConversation, GroupConversationDto> implements IConversationService{

    private static final Logger logger = LogManager.getLogger(GroupConversationService.class);

    private GroupConversationRepository groupConversationRepository;
    private GroupConversationFormatter groupConversationFormatter;

    private PlayerService playerService;
    private MessageService messageService;


    public GroupConversationService(GroupConversationRepository groupConversationRepository,  PlayerService playerService,
                                    MessageService messageService,
                                    GroupConversationFormatter groupConversationFormatter){
        super(groupConversationRepository, groupConversationFormatter);
        this.groupConversationRepository = groupConversationRepository;
        this.groupConversationFormatter = groupConversationFormatter;
        this.playerService = playerService;
        this.messageService = messageService;
    }

    @Override
    @Transactional
    public Conversation createConservation(MessageReceptionDto messageReceptionDto) {
        GroupConversation groupConversation = new GroupConversation();
        Player player1 = this.playerService.selectBySourcePlayerId(messageReceptionDto.getSender().getId());
        List<Player> players = this.playerService.selectBySourcePlayerIdIn(messageReceptionDto.getMembers().stream().map(PlayerLightDto::getId).toList());
        players.add(player1);
        groupConversation.setPlayers(players);
        groupConversation.setMessages(new ArrayList<>());
        groupConversation.setName(getConversationName(groupConversation));
        groupConversation = groupConversationRepository.save(groupConversation);
        MessageDto message = new MessageDto();
        message.setConversationType(ConversationType.GROUP_CONVERSATION);
        message.setContent(messageReceptionDto.getContent());
        message.setConversationId(groupConversation.getId());
        message = messageService.createFromConversation(message, player1);
        return this.selectById(groupConversation.getId());
    }

    private String getConversationName(GroupConversation groupConversation){
        List<String> usernameList = new ArrayList<>();
        for(Player player : groupConversation.getPlayers()){
            usernameList.add(player.getUsername());
        }
        return String.join(", ", usernameList);
    }

    public List<GroupConversationDto> findByPlayersId(Long playerId){
        List<GroupConversation> groupConversationList = groupConversationRepository.findByPlayersId(playerId);
        return groupConversationFormatter.entityToDto(groupConversationList);
    }

}
