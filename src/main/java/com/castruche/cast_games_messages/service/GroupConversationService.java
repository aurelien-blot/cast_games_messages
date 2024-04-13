package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dao.GroupConversationRepository;
import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.entity.GroupConversation;
import com.castruche.cast_games_messages.formatter.GroupConversationFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GroupConversationService extends GenericService<GroupConversation, GroupConversationDto>{

    private static final Logger logger = LogManager.getLogger(GroupConversationService.class);

    private GroupConversationRepository groupConversationRepository;
    private GroupConversationFormatter groupConversationFormatter;


    public GroupConversationService(GroupConversationRepository groupConversationRepository, GroupConversationFormatter groupConversationFormatter){
        super(groupConversationRepository, groupConversationFormatter);
        this.groupConversationRepository = groupConversationRepository;
        this.groupConversationFormatter = groupConversationFormatter;
    }



}
