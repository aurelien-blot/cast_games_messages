package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.ConversationRepository;
import com.castruche.cast_games_messages.dao.GroupConversationRepository;
import com.castruche.cast_games_messages.dao.PrivateConversationRepository;
import com.castruche.cast_games_messages.dto.ConversationDto;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.enums.ConversationType;
import com.castruche.cast_games_messages.formatter.ConversationFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService extends GenericService<Conversation, ConversationDto>{

    private static final Logger logger = LogManager.getLogger(ConversationService.class);

    private ConversationFormatter conversationFormatter;
    private ConversationRepository conversationRepository;
    private GroupConversationRepository groupConversationRepository;
    private PrivateConversationRepository privateConversationRepository;

    public ConversationService(ConversationRepository conversationRepository,
                                 GroupConversationRepository groupConversationRepository,
                                    PrivateConversationRepository privateConversationRepository,
                               ConversationFormatter conversationFormatter){
        super(conversationRepository, conversationFormatter);
        this.conversationRepository = conversationRepository;
        this.groupConversationRepository = groupConversationRepository;
        this.privateConversationRepository = privateConversationRepository;
        this.conversationFormatter = conversationFormatter;
    }
    public Conversation selectById(Long id, ConversationType conversationType){
        if(conversationType.equals(ConversationType.PRIVATE_CONVERSATION)){
            return privateConversationRepository.findById(id).orElse(null);
        }
        else if(conversationType.equals(ConversationType.GROUP_CONVERSATION)){
            return groupConversationRepository.findById(id).orElse(null);
        }
        return null;
    }

    public List<ConversationDto> selectDtoAllByPlayerId(Long playerId){
        List<ConversationDto> results = new ArrayList<>();
       /* List<GroupConversationDto> conversations = groupConversationService.selectByPlayerId(playerId);

        if(conversations != null){
            results = this.(conversations);
        }
        return conversationDtos;*/
        return results;
    }


}
