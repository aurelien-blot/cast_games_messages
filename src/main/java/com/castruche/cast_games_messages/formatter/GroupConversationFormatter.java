package com.castruche.cast_games_messages.formatter;


import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.GroupConversation;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import org.springframework.stereotype.Service;

@Service
public class GroupConversationFormatter implements IFormatter<GroupConversation, GroupConversationDto>{

    private UserFormatter userFormatter;

    public GroupConversationFormatter(UserFormatter userFormatter){
        this.userFormatter = userFormatter;
    }
    @Override
    public GroupConversationDto entityToDto(GroupConversation entity) {
        if(entity == null){
            return null;
        }
        GroupConversationDto dto = new GroupConversationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if(entity.getUsers() != null){
            dto.setUsers(userFormatter.entityToDto(entity.getUsers()));
        }
        return dto;
    }

    @Override
    public GroupConversation dtoToEntity(GroupConversationDto dto) {
        GroupConversation entity = new GroupConversation();
        entity.setId(dto.getId());
        return entity;
    }
}
