package com.castruche.cast_games_messages.service;

import com.castruche.cast_games_messages.dto.ConversationDto;
import com.castruche.cast_games_messages.dto.GroupConversationDto;
import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.entity.Conversation;

public interface IConversationService {

    public Conversation createConservation(MessageReceptionDto messageReceptionDto);


}
