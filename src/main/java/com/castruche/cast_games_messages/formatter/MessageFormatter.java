package com.castruche.cast_games_messages.formatter;

import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.dto.PlayerDto;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.enums.ConversationType;
import com.castruche.cast_games_messages.service.ConversationService;
import com.castruche.cast_games_messages.service.PlayerService;
import com.castruche.cast_games_messages.service.PrivateConversationService;
import org.springframework.stereotype.Service;

@Service
public class MessageFormatter implements IFormatter<Message, MessageDto>{

    private PlayerService playerService;


    public MessageFormatter(PlayerService playerService) {
        this.playerService = playerService;
    }

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
        entity.setContent(dto.getContent());
        entity.setReadAt(dto.getReadAt());
        /*if(dto.getConversationId()!=null){
            Conversation conversation = conversationService.selectById(dto.getConversationId(), dto.getConversationType());
            if(null!=conversation){
                entity.setConversation(conversation);
            }
        }*/
        if(dto.getAuthor()!=null){
            Player player = playerService.selectById(dto.getAuthor().getId());
            if(player != null){
                entity.setAuthor(player);
            }
        }

        return entity;
    }
}
