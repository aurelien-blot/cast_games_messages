package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.ConversationRepository;
import com.castruche.cast_games_messages.dao.GroupConversationRepository;
import com.castruche.cast_games_messages.dao.PrivateConversationRepository;
import com.castruche.cast_games_messages.dto.AbstractDto;
import com.castruche.cast_games_messages.dto.ConversationDto;
import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.GroupConversation;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import com.castruche.cast_games_messages.enums.ConversationType;
import com.castruche.cast_games_messages.formatter.ConversationFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ConversationService extends GenericService<Conversation, ConversationDto>{

    private static final Logger logger = LogManager.getLogger(ConversationService.class);

    private PlayerService playerService;
    private ConversationFormatter conversationFormatter;
    private ConversationRepository conversationRepository;
    private GroupConversationService groupConversationService;
    private PrivateConversationService privateConversationService;

    public ConversationService(PlayerService playerService,
                               ConversationRepository conversationRepository,
                               GroupConversationService groupConversationService,
                               PrivateConversationService privateConversationService,
                               ConversationFormatter conversationFormatter){
        super(conversationRepository, conversationFormatter);
        this.playerService = playerService;
        this.conversationRepository = conversationRepository;
        this.groupConversationService = groupConversationService;
        this.privateConversationService = privateConversationService;
        this.conversationFormatter = conversationFormatter;
    }
    public Conversation selectById(Long id, ConversationType conversationType){
        if(conversationType.equals(ConversationType.PRIVATE_CONVERSATION)){
            return privateConversationService.selectById(id);
        }
        else if(conversationType.equals(ConversationType.GROUP_CONVERSATION)){
            return groupConversationService.selectById(id);
        }
        return null;
    }

    public List<ConversationDto> getConversationListForPlayer(Long sourcePlayerId){
        List<ConversationDto> results = new ArrayList<>();
        Player player = playerService.selectBySourcePlayerId(sourcePlayerId);
        if(player==null){
            return results;
        }
        List<GroupConversationDto> groupConversationList = groupConversationService.findByPlayersId(player.getId());
        results.addAll(groupConversationList);
        List<PrivateConversationDto> privateConversationList = privateConversationService.findByPlayerId(player.getId());
        results.addAll(privateConversationList);
        //On trie les messages de chaque conversation par date de création
        for(ConversationDto conversationDto : results){
            conversationDto.getMessages().sort(Comparator.comparing(AbstractDto::getCreationTime));
        }
        return results;
    }


}
