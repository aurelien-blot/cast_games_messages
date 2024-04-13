package com.castruche.cast_games_messages.formatter;


import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import org.springframework.stereotype.Service;

@Service
public class PrivateConversationFormatter implements IFormatter<PrivateConversation, PrivateConversationDto>{

    private UserFormatter userFormatter;

    public PrivateConversationFormatter(UserFormatter userFormatter){
        this.userFormatter = userFormatter;
    }
    @Override
    public PrivateConversationDto entityToDto(PrivateConversation entity) {
        if(entity == null){
            return null;
        }
        PrivateConversationDto dto = new PrivateConversationDto();
        dto.setId(entity.getId());
        if(entity.getUser1() != null){
            dto.setUser1(userFormatter.entityToDto(entity.getUser1()));
        }
        if(entity.getUser2() != null){
            dto.setUser2(userFormatter.entityToDto(entity.getUser2()));
        }
        return dto;
    }

    @Override
    public PrivateConversation dtoToEntity(PrivateConversationDto dto) {
        PrivateConversation entity = new PrivateConversation();
        entity.setId(dto.getId());
        return entity;
    }
}
