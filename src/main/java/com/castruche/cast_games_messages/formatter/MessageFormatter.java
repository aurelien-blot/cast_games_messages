package com.castruche.cast_games_messages.formatter;

import com.castruche.cast_games_messages.dao.ConversationRepository;
import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.entity.Conversation;
import com.castruche.cast_games_messages.entity.Message;
import com.castruche.cast_games_messages.entity.Player;
import com.castruche.cast_games_messages.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class MessageFormatter implements IFormatter<Message, MessageDto>{

    private PlayerService playerService;
    private ConversationRepository conversationRepository;

     public MessageFormatter(PlayerService playerService, ConversationRepository conversationRepository){
        this.playerService = playerService;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public MessageDto entityToDto(Message entity) {
        if(entity == null){
            return null;
        }
        MessageDto dto = new MessageDto();
        dto.setId(entity.getId());
        dto.setCreationTime(entity.getCreationTime());
        dto.setModificationTime(entity.getModificationTime());
        dto.setContent(entity.getContent());
        dto.setReadAt(entity.getReadAt());
        if(null!=entity.getConversation()){
            dto.setConversationId(entity.getConversation().getId());
        }
        if(null!=entity.getAuthor()){
            dto.setAuthorId(entity.getAuthor().getSourcePlayerId());
        }
        return dto;
    }

    @Override
    public Message dtoToEntity(MessageDto dto) {
        Message entity = new Message();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setReadAt(dto.getReadAt());
        if(dto.getConversationId()!=null){
            Conversation conversation = conversationRepository.findById(dto.getConversationId()).orElse(null);
            if(null!=conversation){
                entity.setConversation(conversation);
            }
        }
       /* if(dto.getAuthorId()!=null){
            Player player = playerService.selectById(dto.getAuthorId());
            if(player != null){
                entity.setAuthor(player);
            }
        }*/

        return entity;
    }
}
