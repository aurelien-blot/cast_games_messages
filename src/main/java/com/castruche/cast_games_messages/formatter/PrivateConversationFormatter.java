package com.castruche.cast_games_messages.formatter;


import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import org.springframework.stereotype.Service;

@Service
public class PrivateConversationFormatter implements IFormatter<PrivateConversation, PrivateConversationDto>{

    private PlayerFormatter playerFormatter;

    public PrivateConversationFormatter(PlayerFormatter playerFormatter){
        this.playerFormatter = playerFormatter;
    }
    @Override
    public PrivateConversationDto entityToDto(PrivateConversation entity) {
        if(entity == null){
            return null;
        }
        PrivateConversationDto dto = new PrivateConversationDto();
        dto.setId(entity.getId());
        if(entity.getPlayer1() != null){
            dto.setPlayer1(playerFormatter.entityToDto(entity.getPlayer1()));
        }
        if(entity.getPlayer2() != null){
            dto.setPlayer2(playerFormatter.entityToDto(entity.getPlayer2()));
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
