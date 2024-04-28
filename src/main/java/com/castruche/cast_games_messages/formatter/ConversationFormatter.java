package com.castruche.cast_games_messages.formatter;


import com.castruche.cast_games_messages.dto.ConversationDto;
import com.castruche.cast_games_messages.dto.ConversationDto;
import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.dto.PrivateConversationDto;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.GroupConversation;
import com.castruche.cast_games_messages.entity.PrivateConversation;
import com.castruche.cast_games_messages.enums.ConversationType;
import org.springframework.stereotype.Service;

@Service
public class ConversationFormatter implements IFormatter<Conversation, ConversationDto>{

    private PrivateConversationFormatter privateConversationFormatter;
    private GroupConversationFormatter groupConversationFormatter;

    public ConversationFormatter(PrivateConversationFormatter privateConversationFormatter, GroupConversationFormatter groupConversationFormatter){
        this.privateConversationFormatter = privateConversationFormatter;
        this.groupConversationFormatter = groupConversationFormatter;
    }

    @Override
    public ConversationDto entityToDto(Conversation entity) {
        if(entity == null){
            return null;
        }
       if(entity instanceof PrivateConversation){
            return privateConversationFormatter.entityToDto((PrivateConversation) entity);
        }
        else if(entity instanceof GroupConversation){
            return groupConversationFormatter.entityToDto((GroupConversation) entity);
        }
        else{
            return null;
        }
    }

    @Override
    public Conversation dtoToEntity(ConversationDto dto) {
        Conversation entity = null;
        if(dto.getType()!=null){
            if(dto.getType().equals(ConversationType.PRIVATE_CONVERSATION)){
                entity = new PrivateConversation();
            }
            else if(dto.getType().equals(ConversationType.GROUP_CONVERSATION)){
                entity = new GroupConversation();
            }
            else{
                return null;
            }
            entity.setId(dto.getId());
        }

        return entity;
    }
}
