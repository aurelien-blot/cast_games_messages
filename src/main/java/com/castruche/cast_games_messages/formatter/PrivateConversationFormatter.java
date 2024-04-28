package com.castruche.cast_games_messages.formatter;


import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import com.castruche.cast_games_messages.enums.ConversationType;
import org.springframework.stereotype.Service;

@Service
public class PrivateConversationFormatter implements IFormatter<PrivateConversation, PrivateConversationDto>{

    private PlayerFormatter playerFormatter;
    private MessageFormatter messageFormatter;

    public PrivateConversationFormatter(PlayerFormatter playerFormatter, MessageFormatter messageFormatter){
        this.playerFormatter = playerFormatter;
        this.messageFormatter = messageFormatter;
    }
    @Override
    public PrivateConversationDto entityToDto(PrivateConversation entity) {
        if(entity == null){
            return null;
        }
        PrivateConversationDto dto = new PrivateConversationDto();
        dto.setType(ConversationType.PRIVATE_CONVERSATION);
        dto.setId(entity.getId());
        if(entity.getPlayer1() != null){
            dto.setPlayer1(playerFormatter.entityToDto(entity.getPlayer1()));
        }
        if(entity.getPlayer2() != null){
            dto.setPlayer2(playerFormatter.entityToDto(entity.getPlayer2()));
        }
        if(entity.getMessages()!=null){
            dto.setMessages(this.messageFormatter.entityToDto(entity.getMessages()));
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
