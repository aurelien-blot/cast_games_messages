package com.castruche.cast_games_messages.formatter;

import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.dto.UserDto;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.User;
import org.springframework.stereotype.Service;

@Service
public class MessageFormatter implements IFormatter<Message, MessageDto>{

    @Override
    public MessageDto entityToDto(Message entity) {
        if(entity == null){
            return null;
        }
        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReadAt(entity.getReadAt());
        if(null!=entity.getConversation()){
            dto.setConversationId(entity.getConversation().getId());
        }
        return dto;
    }

    @Override
    public Message dtoToEntity(MessageDto dto) {
        Message entity = new Message();
        entity.setId(dto.getId());
        return entity;
    }
}
